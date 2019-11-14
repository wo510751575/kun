package com.lj.eshop.eis.controller.order;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.common.CommonConstant;
import com.lj.distributecache.IQueue;
import com.lj.distributecache.RedisCache;
import com.lj.eshop.constant.NoUtil;
import com.lj.eshop.constant.PublicConstants;
import com.lj.eshop.dto.AccWaterDto;
import com.lj.eshop.dto.AccountDto;
import com.lj.eshop.dto.EvlProductDto;
import com.lj.eshop.dto.FindAccWaterPage;
import com.lj.eshop.dto.FindOrderDetailPage;
import com.lj.eshop.dto.FindOrderPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.dto.OrderDetailDto;
import com.lj.eshop.dto.OrderDto;
import com.lj.eshop.dto.PaymentDto;
import com.lj.eshop.dto.ProductDto;
import com.lj.eshop.dto.ProductSkuDto;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.ResponseCode;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.emus.AccWaterAccType;
import com.lj.eshop.emus.AccWaterBizType;
import com.lj.eshop.emus.AccWaterSource;
import com.lj.eshop.emus.AccWaterStatus;
import com.lj.eshop.emus.AccWaterType;
import com.lj.eshop.emus.DelFlag;
import com.lj.eshop.emus.MemberStatus;
import com.lj.eshop.emus.OrderStatus;
import com.lj.eshop.emus.PaymentStatus;
import com.lj.eshop.emus.PaymentType;
import com.lj.eshop.service.IAccWaterService;
import com.lj.eshop.service.IAccountService;
import com.lj.eshop.service.IEvlProductService;
import com.lj.eshop.service.IMemberService;
import com.lj.eshop.service.IOrderDetailService;
import com.lj.eshop.service.IOrderService;
import com.lj.eshop.service.IPaymentService;
import com.lj.eshop.service.IProductService;
import com.lj.eshop.service.IProductSkuService;

/**
 * 
 * 
 * 类说明：订单对外接口集合
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company:
 * @author
 * 
 * 		CreateDate: 2017年9月1日
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IProductSkuService productSkuService;
	@Autowired
	private IOrderDetailService orderDetailService;
	@Autowired
	private IAccWaterService accWaterService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IEvlProductService evlProductService;
	@Autowired
	private IMemberService memberService;
	@Resource
	private IQueue queue;
	@Autowired
	private RedisCache redisCache;

	/**
	 * 抢单
	 */
	@RequestMapping(value = "/grab")
	public ResponseDto grab() {
		try {

			MemberDto memberDto = new MemberDto();
			memberDto.setCode(getLoginMemberCode());
			MemberDto loginMbr = memberService.findMember(memberDto);
			// 如果用户已被冻结，不允许抢单
			if (!MemberStatus.NORMAL.getValue().equals(loginMbr.getStatus())) {
				throw new TsfaServiceException(ResponseCode.USER_UNNORMAL.getCode(),
						ResponseCode.USER_UNNORMAL.getMsg());
			}

			String flag = redisCache.get("grab:" + getLoginMemberCode());
			if (CommonConstant.Y.equals(flag)) {
				// 已在队列中直接返回
				return ResponseDto.successResp();
			}
			queue.rpush(PublicConstants.ORDER_GRAB_LIST, getLoginMemberCode());
			redisCache.set("grab:" + getLoginMemberCode(), CommonConstant.Y);
		} catch (Exception e) {
			logger.error("抢单错误！e={}", e);
		}

		return ResponseDto.successResp();
	}

	/**
	 * 取消抢单
	 */
	@RequestMapping(value = "/unGrab")
	public ResponseDto unGrab() {
		String flag = redisCache.get("grab:" + getLoginMemberCode());
		if (CommonConstant.N.equals(flag)) {
			// 已取消直接返回
			return ResponseDto.successResp();
		}
		queue.lrem(PublicConstants.ORDER_GRAB_LIST, 1, getLoginMemberCode());
		redisCache.set("grab:" + getLoginMemberCode(), CommonConstant.N);
		return ResponseDto.successResp();
	}

	/**
	 * 
	 *
	 * 方法说明：获取订单详情
	 *
	 * @param code
	 * @return
	 *
	 * @author CreateDate: 2017年9月2日
	 *
	 */
	@RequestMapping(value = "/detail")
	public ResponseDto view(String code, String orderNo) {

		if (StringUtils.isEmpty(code) && StringUtils.isEmpty(orderNo)) {
			return ResponseDto.createResp(false, ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMsg(),
					null);
		}

		OrderDto orderDto = null;
		if (StringUtils.isNotEmpty(orderNo)) {
			orderDto = orderService.findOrderByOrderNo(orderNo);
		} else if (StringUtils.isNotEmpty(code)) {
			OrderDto paramDto = new OrderDto();
			paramDto.setCode(code);
			FindOrderPage findOrderPage = new FindOrderPage();
			findOrderPage.setParam(paramDto);
			List<OrderDto> orderDtos = orderService.findOrders(findOrderPage);
			if (orderDtos.size() <= 0) {
				return ResponseDto.createResp(false, ResponseCode.NO_DATA.getCode(), ResponseCode.NO_DATA.getMsg(),
						null);
			}
			orderDto = orderDtos.get(0);
		}
		OrderDetailDto param = new OrderDetailDto();
		param.setOrderNo(orderDto.getOrderNo());
		FindOrderDetailPage findOrderDetailPage = new FindOrderDetailPage();
		findOrderDetailPage.setParam(param);
		orderDto.setDetailDtos(orderDetailService.findOrderDetails(findOrderDetailPage));
		return ResponseDto.successResp(orderDto);
	}

	/**
	 * 
	 *
	 * 方法说明：获取订单明细详情
	 *
	 * @param 订单code code 必填
	 * @return 订单明细code orderDetailCode 必填
	 *
	 * @author 林进权 CreateDate: 2017年9月7日
	 *
	 */
	@RequestMapping(value = "/orderDetail")
	public ResponseDto orderDetail(String code, String orderDetailCode) {
		OrderDto paramDto = new OrderDto();
		paramDto.setCode(code);
		OrderDto orderDto = orderService.findOrder(paramDto);

		OrderDetailDto paramDe = new OrderDetailDto();
		paramDe.setOrderNo(orderDto.getOrderNo());
		paramDe.setCode(orderDetailCode);
		FindOrderDetailPage findOrderDetailPage = new FindOrderDetailPage();
		findOrderDetailPage.setParam(paramDe);
		orderDto.setDetailDtos(orderDetailService.findOrderDetails(findOrderDetailPage));

		return ResponseDto.successResp(orderDto);
	}

	/**
	 * 
	 *
	 * 方法说明：B端订单列表
	 *
	 * @param shopCode
	 * @param status
	 * @return
	 *
	 * @author CreateDate: 2017年9月2日
	 *
	 */
	@RequestMapping(value = { "list" })
	@ResponseBody
	public ResponseDto list(String status, Integer pageNo, Integer pageSize) {
		logger.info("list --> String status={}- start", status);

		OrderDto param = new OrderDto();
		if (StringUtils.isNotEmpty(status)) {
			if (status.contains(",")) {
				param.setStatuss(Arrays.asList(status.split(",")));
			} else {
				param.setStatus(status);
			}
		}

		param.setMbrCode(getLoginMemberCode());
		FindOrderPage findOrderPage = new FindOrderPage();
		findOrderPage.setParam(param);
		if (pageNo != null) {
			findOrderPage.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			findOrderPage.setLimit(pageSize);
		}
		Page<OrderDto> page = orderService.findOrderPage(findOrderPage);
		return ResponseDto.successResp(page);
	}

	/**
	 * 
	 *
	 * 方法说明：确认收款
	 *
	 *
	 */
	@RequestMapping(value = { "receipt" })
	@ResponseBody
	public ResponseDto receipt(String code) {
		logger.info("receipt --> String code={}- start", code);
		if (StringUtils.isEmpty(code)) {
			return ResponseDto.createResp(false, ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMsg(),
					null);
		}

		MemberDto memberDto = getLoginMember();
		if (memberDto == null) {
			return ResponseDto.createResp(false, ResponseCode.ACCESS_VALID.getCode(),
					ResponseCode.ACCESS_VALID.getMsg(), null);
		}

		OrderDto parmDto = new OrderDto();
		parmDto.setCode(code);
		OrderDto orderDto = orderService.findOrder(parmDto);
		if (orderDto.getStatus().equals(OrderStatus.DQR.getValue())) {
			orderService.payment(builPaymentDto(orderDto));
		}
		return ResponseDto.successResp(null);
	}

	/**
	 * 
	 *
	 * 方法说明：取消订单
	 *
	 * @param code
	 * @return
	 *
	 * @author CreateDate: 2017年9月2日
	 *
	 */
	@RequestMapping(value = { "cancel" })
	@ResponseBody
	public ResponseDto cancel(String code) {
		logger.info("cancel --> String code={}- start", code);
		if (StringUtils.isEmpty(code)) {
			return ResponseDto.createResp(false, ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMsg(),
					null);
		}
		MemberDto memberDto = getLoginMember();
		if (memberDto == null) {
			return ResponseDto.createResp(false, ResponseCode.ACCESS_VALID.getCode(),
					ResponseCode.ACCESS_VALID.getMsg(), null);
		}
		OrderDto parmDto = new OrderDto();
		parmDto.setCode(code);
		OrderDto orderDto = orderService.findOrder(parmDto);
		orderService.cancel(orderDto);
		return ResponseDto.successResp(null);
	}

	private PaymentDto builPaymentDto(OrderDto orderDto) {
		/* 获取账户 */
		AccountDto accountDto = accountService.findAccountByMbrCode(orderDto.getMbrCode());
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setAccCode(accountDto.getCode());
		paymentDto.setFee(new BigDecimal(0));
		paymentDto.setOperator(getLoginMemberCode());
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

	/**
	 * 
	 *
	 * 方法说明：订单评论
	 *
	 * @param jsonStr
	 * @return
	 *
	 * @author CreateDate: 2017年9月2日
	 *
	 */
	@RequestMapping(value = { "review" })
	@ResponseBody
	public ResponseDto review(String jsonStr) {
		logger.info("review --> String jsonStr=" + jsonStr + "{}- start");

		if (StringUtils.isEmpty(jsonStr)) {
			return ResponseDto.createResp(false, ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMsg(),
					null);
		}

		/* 获取评论人 */
		MemberDto parmMemberDto = new MemberDto();
		parmMemberDto.setCode(getLoginMember().getCode());
		MemberDto memberDto = memberService.findMember(parmMemberDto);
		jsonStr = jsonStr.replaceAll("\"", "'");

		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		if (!jsonObject.containsKey("orderCode") && !jsonObject.containsKey("evlDtos")) {
			return ResponseDto.createResp(false, ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMsg(),
					null);
		}

		String orderCode = JSONObject.parseObject(jsonStr).getString("orderCode");
		OrderDto parmDto = new OrderDto();
		parmDto.setCode(orderCode);
		OrderDto orderDto = orderService.findOrder(parmDto);
		orderService.review(orderDto);

		JSONArray evlDtos = JSONObject.parseObject(jsonStr).getJSONArray("evlDtos");
		for (int i = 0; i < evlDtos.size(); i++) {
			JSONObject evl = evlDtos.getJSONObject(i);
			String grade = evl.containsKey("grade") ? evl.getString("grade") : "0";
			String info = evl.containsKey("info") ? evl.getString("info") : "";
			String imgs = evl.containsKey("imgs") ? evl.getString("imgs") : "";
			String skuCode = evl.containsKey("skuCode") ? evl.getString("skuCode") : "";

			/* 获取规格商品 */
			ProductSkuDto paramSkuDto = new ProductSkuDto();
			paramSkuDto.setCode(skuCode);
			ProductSkuDto productSkuDto = productSkuService.findProductSku(paramSkuDto);

			/* 获取商品 */
			ProductDto parProductDto = new ProductDto();
			parProductDto.setCode(productSkuDto.getProductCode());
			ProductDto productDto = productService.findProduct(parProductDto);

			EvlProductDto evlProductDto = new EvlProductDto();
			evlProductDto.setEvlGrade(grade);
			evlProductDto.setEvlInfo(info);
			evlProductDto.setEvlMbrCode(memberDto.getCode());
			evlProductDto.setEvlMbrImg(memberDto.getAvotor());
			evlProductDto.setEvlMbrName(memberDto.getName());
			evlProductDto.setGoodCnt(0);
			evlProductDto.setImgs(imgs);
			evlProductDto.setProductCode(productSkuDto.getProductCode());
			evlProductDto.setProductName(productDto.getName());
			evlProductDto.setProductSkuCode(skuCode);
			evlProductDto.setSkuDesc(productSkuDto.getSkuDesc());
			evlProductService.addEvlProduct(evlProductDto);

			/* 修改商品总评分和评论数 */
			productDto.setTotalScore(productDto.getTotalScore() + Integer.valueOf(evlProductDto.getEvlGrade()));
			productDto.setEvlCnt(productDto.getEvlCnt() + 1);
			productService.updateProduct(productDto);

		}
		return ResponseDto.successResp(null);
	}

	/**
	 * 查询一年内的特权购买流水 方法说明：
	 *
	 * @param @return 设定文件
	 * @return List<AccWaterDto> 返回类型
	 * @throws Exception
	 *
	 * @author 林进权 CreateDate: 2017年9月27日
	 */
	private List<AccWaterDto> findAccwatersByRank() {
		AccountDto accountDto = accountService.findAccountByMbrCode(getLoginMemberCode());
		FindAccWaterPage findAccWaterPage = new FindAccWaterPage();
		AccWaterDto accWaterDto = new AccWaterDto();
		accWaterDto.setAccDate(new Date());
		accWaterDto.setAccType(AccWaterAccType.AUTO.getValue());
		accWaterDto.setAccSource(AccWaterSource.ORDER.getValue());
		accWaterDto.setStatus(AccWaterStatus.SUCCESS.getValue());
		accWaterDto.setBizType(AccWaterBizType.PAYMENT.getValue());
		accWaterDto.setWaterType(AccWaterType.SUBTRACT.getValue());
//		accWaterDto.setPayType(AccWaterPayType.RANK.getValue());
		accWaterDto.setAccCode(accountDto.getCode());

		// 一年内的购买记录
		Date endDate = new Date();
		Date startDate = DateUtils.addYears(endDate, -1);
		findAccWaterPage.setStartTime(startDate);
		findAccWaterPage.setEndTime(endDate);

		findAccWaterPage.setParam(accWaterDto);
		return accWaterService.findAccWaters(findAccWaterPage);
	}

}
