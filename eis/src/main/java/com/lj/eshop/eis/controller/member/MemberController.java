package com.lj.eshop.eis.controller.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.eshop.dto.AccWaterDto;
import com.lj.eshop.dto.AccountDto;
import com.lj.eshop.dto.CatalogDto;
import com.lj.eshop.dto.FindAccWaterPage;
import com.lj.eshop.dto.FindCatalogPage;
import com.lj.eshop.dto.FindMemberRankApplyPage;
import com.lj.eshop.dto.FindOrderPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.dto.MemberRankApplyDto;
import com.lj.eshop.dto.OrderDto;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.ResponseCode;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.eis.emus.MemberRankApplyCheck;
import com.lj.eshop.emus.AccWaterBizType;
import com.lj.eshop.emus.AccWaterStatus;
import com.lj.eshop.emus.AccWaterType;
import com.lj.eshop.emus.DelFlag;
import com.lj.eshop.emus.MemberRankApplyStatus;
import com.lj.eshop.emus.OrderStatus;
import com.lj.eshop.service.IAccWaterService;
import com.lj.eshop.service.IAccountService;
import com.lj.eshop.service.ICatalogService;
import com.lj.eshop.service.IMemberRankApplyService;
import com.lj.eshop.service.IMemberService;
import com.lj.eshop.service.IOrderService;

/*
 * 会员对外接口集合.
 * 
 * add by rain at 2017-08-22
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private IMemberService memberService;
	@Autowired
	private IOrderService orderService;
//	@Autowired
//	private IMyAttentionService myAttentionService;
//	@Autowired
//	private IShopService shopService;
	@Autowired
	private IAccWaterService accWaterService;
	@Autowired
	private IAccountService accountService;

//	@Resource
//	public IPmTypeService pmTypeService;
//
//	@Resource
//	public IPersonMemberService personMemberService;
//
	@Autowired
	public IMemberRankApplyService memberRankApplyService;

	@Autowired
	private ICatalogService catalogService;

	/**
	 * 
	 *
	 * 方法说明：客户管理搜索：分页查询
	 *
	 * @param findPmSeachPage
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	@RequestMapping(value = "findPmSeachPage")
	@ResponseBody
	@Deprecated
	public ResponseDto findPmSeachPage(String searchKey, Integer pageNo, Integer pageSize) {
		/*
		 * FindPmSeachPage findPmSeachPage = new FindPmSeachPage();
		 * findPmSeachPage.setMerchantNo(getLoginMerchantCode());
		 * findPmSeachPage.setMemberNoGm(getLoginMemberCode());
		 * findPmSeachPage.setSearchKey(searchKey); if (pageNo != null) {
		 * findPmSeachPage.setStart((pageNo - 1) * pageSize); } if (pageSize != null) {
		 * findPmSeachPage.setLimit(pageSize); } Page<FindPmSeachPageReturn> page =
		 * personMemberService.findPmSeachPage(findPmSeachPage);
		 * 
		 * return ResponseDto.successResp(page);
		 */
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：社交客户列表
	 *
	 * @param pmTypeCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年9月22日
	 *
	 */
	@RequestMapping(value = "/findStIndexPage")
	@ResponseBody
	@Deprecated
	public ResponseDto findStIndexPage(Integer pageNo, Integer pageSize) {
		/*
		 * FindPmTypeIndexPage findPmTypeIndexPage = new FindPmTypeIndexPage();
		 * findPmTypeIndexPage.setMemberNoGm(getLoginMemberCode());
		 * findPmTypeIndexPage.setMerchantNo(getLoginMerchantCode()); if (pageNo !=
		 * null) { findPmTypeIndexPage.setStart((pageNo - 1) * pageSize); } if (pageSize
		 * != null) { findPmTypeIndexPage.setLimit(pageSize); }
		 * findPmTypeIndexPage.setSortDir(PageSortType.desc);
		 * Page<FindPmTypeIndexPageReturn> page =
		 * personMemberService.findPmTypeIndexPage(findPmTypeIndexPage); return
		 * ResponseDto.successResp(page);
		 */
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：客户管理首页：分类信息及其明细查询
	 *
	 * @param findPmTypeIndex
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月11日
	 *
	 */
	@RequestMapping(value = "/findPmTypeIndexAll")
	@ResponseBody
	@Deprecated
	public ResponseDto findPmTypeIndexAll() {

		/*
		 * FindPmTypeIndex findPmTypeIndex = new FindPmTypeIndex();
		 * findPmTypeIndex.setMemberNoGm(getLoginMemberCode());
		 * findPmTypeIndex.setMerchantNo(getLoginMerchantCode());
		 * 
		 * List<FindPmTypeIndexAllReturn> resultList = new
		 * ArrayList<FindPmTypeIndexAllReturn>(); List<FindPmTypeIndexReturn> list =
		 * pmTypeService.findPmTypeIndex(findPmTypeIndex); for (FindPmTypeIndexReturn
		 * findPmTypeIndexReturn : list) { FindPmTypeIndexAllReturn
		 * findPmTypeIndexAllReturn = new FindPmTypeIndexAllReturn();
		 * findPmTypeIndexAllReturn.setPmTye(findPmTypeIndexReturn); FindPmTypeIndexPage
		 * findPmTypeIndexPage = new FindPmTypeIndexPage();
		 * findPmTypeIndexPage.setLimit(Constans.LIMIT);
		 * findPmTypeIndexPage.setMemberNoGm(findPmTypeIndex.getMemberNoGm());
		 * findPmTypeIndexPage.setMerchantNo(findPmTypeIndex.getMerchantNo());
		 * findPmTypeIndexPage.setPmTypeCode(findPmTypeIndexReturn.getCode());
		 * findPmTypeIndexPage.setShopNo(findPmTypeIndex.getShopNo());
		 * Page<FindPmTypeIndexPageReturn> page =
		 * personMemberService.findPmTypeIndexPage(findPmTypeIndexPage);
		 * findPmTypeIndexAllReturn.setDetail(page.getRows());
		 * resultList.add(findPmTypeIndexAllReturn); } return
		 * ResponseDto.successResp(resultList);
		 */
		return null;
	}

	/**
	 * 
	 *
	 * 方法说明：C端个人中心
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年9月5日
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
	 * 
	 *
	 * 方法说明：B端个人中心
	 *
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年9月5日
	 *
	 */
	@RequestMapping(value = { "my_b" })
	@ResponseBody
	public ResponseDto my_b() {
		Map<String, Object> returnMap = new HashMap<>();

//		FindShopPage findShopPage = new FindShopPage();
//		ShopDto paramShop = new ShopDto();
//		paramShop.setCode(getLoginShopCode());
//		findShopPage.setParam(paramShop);
//		List<ShopDto> shopList = shopService.findShops(findShopPage);
//		ShopDto shopDto = shopList.get(0);
//		returnMap.put("shop", shopDto);

		MemberDto memberDto = new MemberDto();
		memberDto.setCode(getLoginMemberCode());
		returnMap.put("member", memberService.findMember(memberDto));

		FindOrderPage findOrderPage = new FindOrderPage();
		OrderDto param = new OrderDto();
		param.setMbrCode(getLoginMemberCode());
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

		/* 当天收益 */
		Date now = new Date();
		Date startTime = DateUtils.getDateByFirstSeconds(now);
		Date endTime = DateUtils.getDateByLastSeconds(now);

		/* 统计流水收益 */
		BigDecimal monthAmt = new BigDecimal("0");
		try {
			AccountDto accountDto = accountService.findAccountByMbrCode(getLoginMemberCode());
			AccWaterDto paramWaterDto = new AccWaterDto();
			paramWaterDto.setAccCode(accountDto.getCode());
			paramWaterDto.setWaterType(AccWaterType.ADD.getValue());
			paramWaterDto.setBizType(AccWaterBizType.COMMISSION.getValue());
			paramWaterDto.setStatus(AccWaterStatus.SUCCESS.getValue());
			FindAccWaterPage findAccWaterPage = new FindAccWaterPage();
			findAccWaterPage.setParam(paramWaterDto);
			findAccWaterPage.setStartTime(startTime);
			findAccWaterPage.setEndTime(endTime);
			monthAmt = accWaterService.findIncomeAmt(findAccWaterPage);
		} catch (Exception e) {
			logger.error("my_b 当天收益>>", e);
			monthAmt = new BigDecimal("0");
		}
		// 今日收益
		returnMap.put("dayAmt", monthAmt == null ? new BigDecimal(0) : monthAmt);

		/* 今日订单 */
		param.setStatus(null);
		param.setStartTime(DateUtils.formatDate(startTime, DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
		param.setEndTime(DateUtils.formatDate(endTime, DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
		findOrderPage.setParam(param);

		// 自己今天的单
		int dayCount = orderService.findOrderPageCount(findOrderPage);

		// 下级今天的单
		param.setMyInvite(getLoginMemberCode());
		param.setMbrCode(null);
		int dayTeamOrderCount = orderService.findOrderPageCount(findOrderPage);
		returnMap.put("dayCount", dayCount + dayTeamOrderCount);// 今日订单包含下级今天的单

		// 团队订单，只取下级的，不管订单状态
		FindOrderPage teamOrderPage = new FindOrderPage();
		OrderDto teamOrderparam = new OrderDto();
		teamOrderparam.setMyInvite(getLoginMemberCode());
		teamOrderPage.setParam(teamOrderparam);
		int teamOrderCount = orderService.findOrderPageCount(teamOrderPage);
		returnMap.put("teamOrderCount", teamOrderCount);

		// 下级成员
		List<String> mbrCodes = memberRankApplyService.findMemberCodesByInvite(getLoginMemberCode());
		returnMap.put("teamCount", mbrCodes.size());

		/* 粉丝人数 */
		/*
		 * FindMyAttentionPage findMyAttentionPage = new FindMyAttentionPage();
		 * MyAttentionDto paramMy = new MyAttentionDto();
		 * paramMy.setShopCode(shopDto.getCode());
		 * findMyAttentionPage.setParam(paramMy); int attCount =
		 * myAttentionService.findMyAttentionPageCount(findMyAttentionPage);
		 * 
		 */

		return ResponseDto.successResp(returnMap);
	}

	/**
	 * 
	 *
	 * 方法说明：修改客户分组
	 *
	 * @param pmTypeCode
	 * @param memberNo
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年9月22日
	 *
	 */
	@RequestMapping(value = "changePmType")
	@ResponseBody
	@Deprecated
	public ResponseDto changePmType(String pmTypeCode, String memberNo) {

		/*
		 * ChangePmTypeUngroup changePmTypeUngroup = new ChangePmTypeUngroup();
		 * changePmTypeUngroup.setMemberNo(memberNo);
		 * changePmTypeUngroup.setMemberNoGm(getLoginMemberCode());
		 * changePmTypeUngroup.setMerchantNo(getLoginMerchantCode());
		 * changePmTypeUngroup.setPmTypeCode(pmTypeCode);
		 * 
		 * pmTypeService.changePmType_app_ungroup(changePmTypeUngroup);
		 */
		return ResponseDto.successResp(null);
	}

	/**
	 * 
	 *
	 * 方法说明：修改客户资料
	 *
	 * @param editPersonMember
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年9月22日
	 *
	 */
	@RequestMapping(value = "modifyMemberInfo")
	@ResponseBody
	@Deprecated
	public ResponseDto modifyMemberInfo(String paramJson) {
		/*
		 * logger.debug("modifyMemberInfo(String paramJson={}) - start", paramJson);
		 * EditPersonMember editPersonMember = JsonUtils.toObj(paramJson,
		 * EditPersonMember.class);
		 * editPersonMember.setMemberNoGm(getLoginMemberCode());
		 * editPersonMember.setMerchantNo(getLoginMerchantCode());
		 * personMemberService.editPersonMember(editPersonMember);
		 */
		return ResponseDto.successResp(null);
	}

	/**
	 * 
	 *
	 * 方法说明：查询客户详情
	 *
	 * @param memberNo
	 * @param memberNoGm
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年9月22日
	 *
	 */
	@RequestMapping(value = "findPmInfo")
	@ResponseBody
	@Deprecated
	public ResponseDto findPmInfo(String memberNo) {
		/*
		 * FindPmInfoAll findPmInfoAll = new FindPmInfoAll();
		 * findPmInfoAll.setMemberNo(memberNo);
		 * findPmInfoAll.setMemberNoGm(getLoginMemberCode()); FindPmInfoAllReturn
		 * findPmInfoAllReturn = personMemberService.findPmInfoAll(findPmInfoAll);
		 * return ResponseDto.successResp(findPmInfoAllReturn);
		 */
		return null;
	}

	/**
	 * 
	 * 方法说明：我的客户-新增客户，提供客户职业组基本信息查询
	 * 
	 * @param
	 * @return
	 *
	 * @author rain CreateDate: 2017年7月3日
	 *
	 */
	@RequestMapping(value = "inqueryMemberJobInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto inqueryMemberJobInfo(String merchantNo, HttpServletRequest httpServletRequest) {
		/*
		 * List<MemLine> list = memLineService.inqueryMemberJobInfo(merchantNo); return
		 * ResponseDto.successResp(list);
		 */
		return null;
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
	 * @Title: getByCode @Description: 根据会员编号获取会员信息 @param: @param
	 *         code @param: @return @return: ResponseDto @throws
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
	 * 
	 * @Title: get @Description: 获取当前登录会员信息 @param: @return @return:
	 *         ResponseDto @throws
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
	 * 会员状态-用于会员申请填写地址和选礼包页面 1.会员是否申请开通 2.会员是否已支付 3.会员是否填写收货地址 4.会员申请时选择的开通年限
	 * 5.会员是否选择礼包
	 */
	@RequestMapping(value = "/status")
	@ResponseBody
	public ResponseDto status() {

		String mbrCode = getLoginMemberCode();

		FindMemberRankApplyPage findMemberRankApplyPage = new FindMemberRankApplyPage();
		MemberRankApplyDto param = new MemberRankApplyDto();
		param.setMemberCode(mbrCode);
		param.setDelFlag(DelFlag.N.getValue());
//		param.setStatus(MemberRankApplyStatus.WAIT.getValue());
		findMemberRankApplyPage.setParam(param);

		// 未申请
		List<MemberRankApplyDto> list = memberRankApplyService.findMemberRankApplys(findMemberRankApplyPage);
		if (list.isEmpty() || list.size() <= 0) {
			return ResponseDto.createResp(true, MemberRankApplyCheck.NOT_APPLY.getValue(),
					MemberRankApplyCheck.NOT_APPLY.getChName(), null);
		}

		// 未支付
		MemberRankApplyDto applyDto = list.get(0);
		if (!MemberRankApplyStatus.SUCCESS.getValue().equals(applyDto.getStatus())) {
			logger.info("特权未支付--------------applyDto={}", applyDto);
			return ResponseDto.createResp(true, MemberRankApplyCheck.IN_APPLY.getValue(),
					MemberRankApplyCheck.IN_APPLY.getChName(), null);
		}

		// 未绑定手机号码或填写密码
		MemberDto findM = new MemberDto();
		findM.setCode(mbrCode);
		MemberDto ms = memberService.findMember(findM);
		if (null != ms) {
			if (StringUtils.isEmpty(ms.getPhone()) || StringUtils.isEmpty(ms.getPassword())) {
				logger.info("未绑定手机号码或填写密码----");
				return ResponseDto.createResp(true, MemberRankApplyCheck.NOT_MOBILE.getValue(),
						MemberRankApplyCheck.NOT_MOBILE.getChName(), null);
			}
		}

		// 未补充信息
		FindOrderPage findOrderPage = new FindOrderPage();
		OrderDto paramOrder = new OrderDto();
		paramOrder.setMbrCode(mbrCode);
		paramOrder.setGiftType(true);
		findOrderPage.setParam(paramOrder);
		List<OrderDto> orders = orderService.findOrders(findOrderPage);
		if (orders.isEmpty() || orders.size() <= 0) {
			return ResponseDto.createResp(true, MemberRankApplyCheck.IN_APPLY.getValue(),
					MemberRankApplyCheck.IN_APPLY.getChName(), null);
		}

		OrderDto orderDto = orders.get(0);
		if (StringUtils.isEmpty(orderDto.getRevicePhone()) || StringUtils.isEmpty(orderDto.getRevicerName())) {
			return ResponseDto.createResp(true, MemberRankApplyCheck.FAIL.getValue(),
					MemberRankApplyCheck.FAIL.getChName(), applyDto.getGiftCode());
		}
		return ResponseDto.createResp(true, MemberRankApplyCheck.NORMAL.getValue(),
				MemberRankApplyCheck.NORMAL.getChName(), applyDto.getGiftCode());
	}

	/**
	 * 我邀请的会员
	 */
	@RequestMapping(value = "myInvite")
	@ResponseBody
	public ResponseDto myInvite(Integer pageNo, Integer pageSize) {

		String mbrCode = getLoginMemberCode();
		if (StringUtils.isEmpty(mbrCode)) {
			return ResponseDto.getErrorResponse(ResponseCode.UN_LOGIN);
		}
		FindMemberRankApplyPage findMemberRankApplyPage = new FindMemberRankApplyPage();
		MemberRankApplyDto param = new MemberRankApplyDto();
		param.setMyInvite(mbrCode);
		param.setDelFlag(DelFlag.N.getValue());
		param.setStatus(MemberRankApplyStatus.SUCCESS.getValue());
		findMemberRankApplyPage.setParam(param);
		if (pageNo != null) {
			findMemberRankApplyPage.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			findMemberRankApplyPage.setLimit(pageSize);
		}
		Page<MemberRankApplyDto> page = memberRankApplyService.findMemberByMyInvitePage(findMemberRankApplyPage);

		return ResponseDto.successResp(page);
	}
}
