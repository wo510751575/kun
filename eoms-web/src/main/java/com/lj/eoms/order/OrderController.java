package com.lj.eoms.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.web.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.core.util.StringUtils;
import com.lj.distributecache.IQueue;
import com.lj.distributecache.RedisCache;
import com.lj.eoms.utils.UserUtils;
import com.lj.eshop.constant.NoUtil;
import com.lj.eshop.constant.PublicConstants;
import com.lj.eshop.dto.AccountDto;
import com.lj.eshop.dto.AccountInfoDto;
import com.lj.eshop.dto.FindAccountInfoPage;
import com.lj.eshop.dto.FindOrderPage;
import com.lj.eshop.dto.OrderDto;
import com.lj.eshop.dto.PaymentDto;
import com.lj.eshop.emus.AccWaterPayType;
import com.lj.eshop.emus.AccWaterSource;
import com.lj.eshop.emus.DelFlag;
import com.lj.eshop.emus.OrderInvoice;
import com.lj.eshop.emus.OrderStatus;
import com.lj.eshop.emus.PaymentStatus;
import com.lj.eshop.emus.PaymentType;
import com.lj.eshop.service.IAccountInfoService;
import com.lj.eshop.service.IAccountService;
import com.lj.eshop.service.IOrderService;
import com.lj.eshop.service.IPaymentService;

/**
 * 
 * 
 * 类说明：订单
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company:
 * @author
 * 
 * 		CreateDate: 2017年7月22日
 */
@Controller
@RequestMapping("${adminPath}/order/order")
public class OrderController extends BaseController {

	public static final String LIST = "modules/order/list";
	public static final String FORM = "modules/order/form";
	public static final String EDIT = "modules/order/edit";
	public static final String VIEW = "modules/order/view";

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private IAccountService accountService;
	@Resource
	private IQueue queue;
	@Autowired
	private IAccountInfoService accountInfoService;
	@Autowired
	private RedisCache redisCache;

	/** 列表 */
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "list", "" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String list(OrderDto param, Integer pageNo, Integer pageSize, Model model) {
		try {
			param.setMerchantCode(UserUtils.getUser().getMerchant().getCode());
			param.setGiftType(null);
			FindOrderPage findOrderPage = new FindOrderPage();
			findOrderPage.setSortBy("create_time");
			findOrderPage.setSortDir(PageSortType.desc);
			findOrderPage.setParam(param);
			if (pageNo != null) {
				findOrderPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findOrderPage.setLimit(pageSize);
			}
			Page<OrderDto> pageDto = orderService.findOrderPage(findOrderPage);
			List<OrderDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			com.ape.common.persistence.Page<OrderDto> page = new com.ape.common.persistence.Page<OrderDto>(
					pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();
			model.addAttribute("page", page);
			model.addAttribute("orderStatus", OrderStatus.values());
			model.addAttribute("payTypes", AccWaterPayType.values());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LIST;
	}

	/** 视图 */
	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "form", "view" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String form(String code, Model model) {
		try {
			OrderDto param = new OrderDto();
			param.setCode(code);
			OrderDto dto = orderService.findOrder(param);

			FindOrderPage findOrderPage = new FindOrderPage();
			findOrderPage.setParam(param);
			Page<OrderDto> page = orderService.findOrderEisPage(findOrderPage);
			if (!page.getRows().isEmpty() && page.getRows().size() > 0) {
				OrderDto order = page.getRows().iterator().next();
				model.addAttribute("detailDtos", order.getDetailDtos());
			}
			model.addAttribute("data", dto);
			model.addAttribute("orderStatus", OrderStatus.values());
			model.addAttribute("payTypes", AccWaterPayType.values());
			model.addAttribute("invoices", OrderInvoice.values());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return VIEW;
	}

	/** 发货 */
	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "shipping")
	@ResponseBody
	public boolean shipping(OrderDto paramDto, String expressNo, String expressName,
			RedirectAttributes redirectAttributes) {
		try {
			OrderDto orderDto = orderService.findOrder(paramDto);
			orderService.shipping(orderDto, expressNo, expressName);
			addMessage(redirectAttributes, "订单：" + orderDto.getOrderNo() + "发货成功");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发货错误e={}", e);
			return false;
		}
		return true;
//		return "redirect:" + adminPath + "/order/order/";
	}

	/** 收款 */
	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "payment")
	public String payment(String code, String orderNo, BigDecimal amt, RedirectAttributes redirectAttributes) {

		OrderDto parmOrderDto = new OrderDto();
		parmOrderDto.setCode(code);
		OrderDto orderDto = orderService.findOrder(parmOrderDto);

		orderService.payment(builPaymentDto(orderDto));
		addMessage(redirectAttributes, "订单：" + orderNo + "收款成功");
		return "redirect:" + adminPath + "/order/order/";
	}

	/**
	 * 
	 *
	 * 方法说明：生成预付单
	 *
	 * @param orderDto
	 * @return
	 *
	 * @author CreateDate: 2017年9月9日
	 *
	 */
	private PaymentDto builPaymentDto(OrderDto orderDto) {
		/* 获取账户 */
		AccountDto accountDto = accountService.findAccountByMbrCode(orderDto.getMbrCode());
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setAccCode(accountDto.getCode());
		paymentDto.setFee(new BigDecimal(0));
		paymentDto.setOperator(UserUtils.getUser().getName());
		paymentDto.setPayer(orderDto.getMbrCode());
		paymentDto.setPaymentDate(new Date());
		paymentDto.setPaymentMethod(orderDto.getPayType());
		paymentDto.setSn(NoUtil.generateNo(NoUtil.JY));
		paymentDto.setStatus(PaymentStatus.SUCCESS.getValue());
		paymentDto.setType(PaymentType.OFFLINE.getValue());
		paymentDto.setMbrCode(orderDto.getMbrCode());
		paymentDto.setBizNo(orderDto.getOrderNo());
		paymentDto.setDelFlag(DelFlag.N.getValue());
		paymentDto.setAmount(orderDto.getAmt());
		paymentDto.setAccSource(AccWaterSource.ORDER.getValue());
		paymentService.addPayment(paymentDto);
		return paymentDto;
	}

	/** 取消 */
	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "cancel")
	public String cancel(OrderDto paramDto, RedirectAttributes redirectAttributes) {
		OrderDto orderDto = orderService.findOrder(paramDto);
		orderService.cancel(orderDto);
		addMessage(redirectAttributes, "订单：" + orderDto.getOrderNo() + "取消成功");
		return "redirect:" + adminPath + "/order/order/";
	}

	@RequestMapping(value = "viewH5")
	public String viewH5(Model model) {
		try {
			// 从队列中取出第一个
			String mbrCode = queue.lpop(PublicConstants.ORDER_GRAB_LIST);
			if (StringUtils.isNotEmpty(mbrCode)) {
				redisCache.set("grab:" + mbrCode, "N");
				// 等待用户操作过后再移除 TODO
//				queue.lpush(PublicConstants.ORDER_GRAB_LIST, mbrCode);
				FindAccountInfoPage findAccountInfoPage = new FindAccountInfoPage();
				AccountInfoDto param = new AccountInfoDto();
				param.setMbrCode(mbrCode);
				findAccountInfoPage.setParam(param);
				List<AccountInfoDto> list = accountInfoService.findAccountInfos(findAccountInfoPage);
				model.addAttribute("list", list);
				model.addAttribute("types", AccWaterPayType.values());
				model.addAttribute("mbrCode", mbrCode);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/order/viewH5";
	}

	@RequestMapping(value = "create")
	@ResponseBody
	public boolean create(String mbrCode) {
		try {
			orderService.createOrder(mbrCode, new BigDecimal(5000), AccWaterPayType.ALIQRCODE.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发货错误e={}", e);
			return false;
		}
		return true;
	}
}
