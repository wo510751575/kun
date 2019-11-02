package com.lj.eshop.eis.controller.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.eshop.dto.CatalogDto;
import com.lj.eshop.dto.FindCatalogPage;
import com.lj.eshop.dto.FindOrderPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.dto.OrderDto;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.MobilePhoneLoginDto;
import com.lj.eshop.eis.dto.ResponseCode;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.eis.dto.TokenDto;
import com.lj.eshop.eis.service.IUserLoginService;
import com.lj.eshop.eis.utils.AuthCodeUtils;
import com.lj.eshop.emus.CodeCheckBizType;
import com.lj.eshop.emus.DelFlag;
import com.lj.eshop.emus.OrderStatus;
import com.lj.eshop.service.ICatalogService;
import com.lj.eshop.service.IMemberService;
import com.lj.eshop.service.IOrderService;

/**
 * 
 * @ClassName: MemberController
 * @Description:
 * @author:
 * @date: 2019-11-02 16:39
 * 
 * @Copyright:
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private IMemberService memberService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	public IUserLoginService userLoginService;
	@Autowired
	private ICatalogService catalogService;

	@RequestMapping(value = { "register" })
	@ResponseBody
	public ResponseDto register(MemberDto param, String authCode) {
		if (StringUtils.isEmpty(param.getMerchantCode()) || StringUtils.isEmpty(param.getPhone())
				|| StringUtils.isEmpty(authCode) || StringUtils.isEmpty(param.getPassword())) {
			return ResponseDto.getErrorResponse(ResponseCode.PARAM_ERROR);
		}
		// 校验验证码
		AuthCodeUtils.validAuthCode(param.getPhone(), CodeCheckBizType.LOGIN.getValue(), authCode,
				AuthCodeUtils.AUTH_CODE_VALID_TIME);

		MemberDto memberDto = userLoginService.findMemberDtoByPhone(param.getPhone());
		if (memberDto != null) {
			return ResponseDto.getErrorResponse(ResponseCode.PHONE_EXIST);
		}
		memberService.addMember(memberDto);
		return ResponseDto.successResp();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto mobilePhoneLogin(MobilePhoneLoginDto dto) {
		TokenDto tk = userLoginService.mobilePhoneLogin(dto);
		return ResponseDto.successResp(tk);
	}

	/**
	 * 
	 *
	 * 方法说明：C端个人中心
	 *
	 * @return
	 *
	 * @author CreateDate: 2017年9月5日
	 *
	 */
	@RequestMapping(value = { "my_c" })
	@ResponseBody
	public ResponseDto my_c() {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		/* 会员信息 */
		MemberDto parmMemberDto = new MemberDto();
		parmMemberDto.setCode(getLoginMemberCode());
		MemberDto memberDto = memberService.findMember(parmMemberDto);
		returnMap.put("member", memberDto);
		/* 总消费金额 */
		FindOrderPage findOrderPage = new FindOrderPage();
		OrderDto param = new OrderDto();
		param.setMbrCode(memberDto.getCode());
		findOrderPage.setParam(param);
		BigDecimal totalAmt;
		try {
			totalAmt = orderService.findAmtSum(findOrderPage);
		} catch (Exception e) {
			logger.error("my_c 总消费金额>>", e);
			totalAmt = new BigDecimal("0");
		}
		returnMap.put("totalAmt", totalAmt);

		/* 总待付款量 */
		param.setStatus(OrderStatus.UNPAID.getValue());
		findOrderPage.setParam(param);
		int unPaid = orderService.findOrderPageCount(findOrderPage);
		returnMap.put("unPaidCount", unPaid);

		/* 总待收货量 */
		param.setStatus(OrderStatus.SHIPPED.getValue());
		findOrderPage.setParam(param);
		int shippedCount = orderService.findOrderPageCount(findOrderPage);
		returnMap.put("shippedCount", shippedCount);

		/* 总待评论 */
		param.setStatus(OrderStatus.UNEVL.getValue());
		findOrderPage.setParam(param);
		int evlCount = orderService.findOrderPageCount(findOrderPage);
		returnMap.put("evlCount", evlCount);

		/* 本月消费金额 */
		param.setStatus(null);
		param.setStartTime(DateUtils.formatDate(DateUtils.getMonthFirstDay(), DateUtils.PATTERN_yyyy_MM_dd));
		param.setEndTime(DateUtils.formatDate(DateUtils.getMonthLastDay(), DateUtils.PATTERN_yyyy_MM_dd));
		findOrderPage.setParam(param);
		BigDecimal monthAmt;
		try {
			monthAmt = orderService.findAmtSum(findOrderPage);
		} catch (Exception e) {
			logger.error("my_c 本月消费金额>>", e);
			monthAmt = new BigDecimal(0);
		}
		returnMap.put("monthAmt", monthAmt);

		return ResponseDto.successResp(returnMap);
	}

	/**
	 * 方法说明：查询喜好列表。
	 * 
	 * @return
	 * @author lhy 2017年9月25日
	 *
	 */
	@RequestMapping(value = "findInterestList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto findInterestList() {
		List<CatalogDto> list = new ArrayList<CatalogDto>();
		CatalogDto catalogDto = new CatalogDto();
		catalogDto.setDelFlag(DelFlag.N.getValue());
		catalogDto.setParentCatalogCode(CatalogDto.getRootId());
		FindCatalogPage findCatalogPage = new FindCatalogPage();
		findCatalogPage.setParam(catalogDto);
		List<CatalogDto> sourcelist = catalogService.findCatalogs(findCatalogPage);
		CatalogDto.sortList(list, sourcelist, CatalogDto.getRootId(), true);
		List<CatalogDto> rtList = new ArrayList<CatalogDto>();
		for (int i = 0; i < list.size(); i++) {
			CatalogDto s = list.get(i);
			CatalogDto ca = new CatalogDto();
			ca.setCode(s.getCode());
			ca.setCatalogName(s.getCatalogName());
			rtList.add(ca);
		}
		return ResponseDto.successResp(rtList);
	}

	/**
	 * 
	 * 根据会员编号获取会员信息
	 */
	@RequestMapping(value = "getByCode")
	@ResponseBody
	public ResponseDto getByCode(String code) {
		AssertUtils.notNullAndEmpty(code, "编号不能为空");
		MemberDto memberDto = new MemberDto();
		memberDto.setCode(code);
		MemberDto dto = memberService.findMember(memberDto);
		return ResponseDto.successResp(dto);
	}

	/**
	 * 获取当前登录会员信息
	 */
	@RequestMapping(value = "get")
	@ResponseBody
	public ResponseDto get() {
		MemberDto memberDto = new MemberDto();
		memberDto.setCode(getLoginMemberCode());
		MemberDto dto = memberService.findMember(memberDto);
		return ResponseDto.successResp(dto);
	}

	/**
	 * 我邀请的会员
	 */
	@RequestMapping(value = "myInvite")
	@ResponseBody
	public ResponseDto myInvite(Integer pageNo, Integer pageSize) {

		return ResponseDto.successResp();
	}
}
