/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eoms.member;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.web.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.eoms.dto.ShopMemberDto;
import com.lj.eoms.entity.sys.User;
import com.lj.eoms.utils.UserUtils;
import com.lj.eoms.utils.Validator;
import com.lj.eoms.utils.excel.ExportExcel;
import com.lj.eoms.utils.excel.ImportExcel;
import com.lj.eshop.dto.AccWaterDto;
import com.lj.eshop.dto.AccountDto;
import com.lj.eshop.dto.AccountInfoDto;
import com.lj.eshop.dto.FindAccWaterPage;
import com.lj.eshop.dto.FindAccountInfoPage;
import com.lj.eshop.dto.FindMemberPage;
import com.lj.eshop.dto.FindOrderPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.dto.OrderDto;
import com.lj.eshop.emus.AccWaterBizType;
import com.lj.eshop.emus.AccWaterStatus;
import com.lj.eshop.emus.AccWaterType;
import com.lj.eshop.emus.MemberGrade;
import com.lj.eshop.emus.MemberSex;
import com.lj.eshop.emus.MemberSourceFrom;
import com.lj.eshop.emus.MemberStatus;
import com.lj.eshop.emus.MemberType;
import com.lj.eshop.emus.OrderStatus;
import com.lj.eshop.service.IAccWaterService;
import com.lj.eshop.service.IAccountInfoService;
import com.lj.eshop.service.IAccountService;
import com.lj.eshop.service.IMemberService;
import com.lj.eshop.service.IOrderService;

/**
 * 
 * 类说明：门店会员 。
 * 
 * <p>
 * 
 * @Company:
 * @author lhy CreateDate: 2017-8-25
 */
@Controller
@RequestMapping("${adminPath}/member/member/")
public class MemberController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	public static final String LIST = "modules/member/member/list";

	public static final String FORM = "modules/member/member/form";
	public static final String EDIT = "modules/member/member/edit";
	public static final String VIEW = "modules/member/member/view";
	@Autowired
	private IMemberService memberService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IAccountInfoService accountInfoService;
	@Autowired
	private IAccWaterService accWaterService;
	@Autowired
	private IAccountService accountService;

	/** 列表 */
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindMemberPage memberPage, Integer pageNo, Integer pageSize, Model model) {
		MemberDto member = memberPage.getParam();
		if (member == null) {
			member = new MemberDto();
		}

		User user = UserUtils.getUser();
		if (null != user.getMerchant()) {
			member.setMerchantCode(UserUtils.getUser().getMerchant().getCode());// 这里设置用户所属商户

			if ("3".equals(user.getOffice().getGrade())) {
				// 小组增加过滤
				member.setOfficeId(user.getOffice().getId());
			}
			List<String> types = new ArrayList<String>();// 查询的类型 1,2,3
			types.add(MemberType.CLIENT.getValue());
			types.add(MemberType.SHOP.getValue());
			// types.add(MemberType.SHOP_AND_CLIENT.getValue());

			memberPage.setTypes(types);
			memberPage.setParam(member);
			memberPage.setSortDir(PageSortType.desc);
			memberPage.setSortBy("create_time");

			if (pageNo != null) {
				memberPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				memberPage.setLimit(pageSize);
			}
			Page<MemberDto> pageDto = memberService.findMemberPage(memberPage);
			List<MemberDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());
			com.ape.common.persistence.Page<MemberDto> page = new com.ape.common.persistence.Page<MemberDto>(
					pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();

			String systemAliasName = "ms";
			String groupName = "share";
			String key = "shareUrl";
			String url = localCacheSystemParams.getSystemParam(systemAliasName, groupName, key);

			model.addAttribute("page", page);
			model.addAttribute("grades", MemberGrade.values());
			model.addAttribute("statuss", MemberStatus.values());
			model.addAttribute("sexs", MemberSex.values());
			model.addAttribute("types", MemberType.values());
			model.addAttribute("memberPage", memberPage);
			model.addAttribute("sourceFroms", MemberSourceFrom.values());
			model.addAttribute("merchantCode", UserUtils.getUser().getMerchant().getCode());
			model.addAttribute("url", url);
		}
		return LIST;
	}

	/**
	 * 新增 无新增 @RequiresPermissions("member:member:edit")
	 * 
	 * @RequestMapping(value = "/save", method = RequestMethod.POST) public String
	 *                       save(MemberDto memberDto, RedirectAttributes
	 *                       redirectAttributes) { memberDto.setCreateTime(new
	 *                       Date());
	 *                       memberDto.setMerchantCode(UserUtils.getUser().getMerchant().getCode());//
	 *                       这里设置用户所属商户 memberService.addMember(memberDto);
	 *                       addMessage(redirectAttributes, "保存客户'" +
	 *                       memberDto.getName() + "'成功"); return "redirect:" +
	 *                       adminPath + "/member/member/"; }
	 */

	/** 新增/修改/查看表单 */
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = "/form")
	public String form(String code, Boolean isView, Model model) {
		MemberDto param = new MemberDto();
		param.setCode(code);
		MemberDto rtDto = memberService.findMember(param);
		model.addAttribute("data", rtDto);
		model.addAttribute("grades", MemberGrade.values());
		model.addAttribute("statuss", MemberStatus.values());
		model.addAttribute("sexs", MemberSex.values());
		model.addAttribute("types", MemberType.values());
		model.addAttribute("sourceFroms", MemberSourceFrom.values());
		if (isView != null && isView) {
			// 接单总数量，接单总金额，接单成功率，补单率，收款码列表，收入金额
			FindOrderPage findOrderPage = new FindOrderPage();
			OrderDto orderP = new OrderDto();
			orderP.setMbrCode(code);
			findOrderPage.setParam(orderP);
			int totalCount = orderService.findOrderPageCount(findOrderPage);
			model.addAttribute("totalCount", totalCount);

			BigDecimal totalAmt = orderService.findAmtSum(findOrderPage);
			model.addAttribute("totalAmt", totalAmt);

			orderP.setStatus(OrderStatus.COMPLETED.getValue());
			int successCount = orderService.findOrderPageCount(findOrderPage);

			BigDecimal successCalc = totalCount == 0 ? BigDecimal.ZERO
					: new BigDecimal(successCount).divide(new BigDecimal(totalCount)).setScale(2, RoundingMode.HALF_UP)
							.multiply(new BigDecimal(100));
			model.addAttribute("successCalc", successCalc);

			// 补单
			orderP.setGiftType(true);
			int bdCount = orderService.findOrderPageCount(findOrderPage);
			BigDecimal bdCalc = totalCount == 0 ? BigDecimal.ZERO
					: new BigDecimal(bdCount).divide(new BigDecimal(totalCount)).setScale(2, RoundingMode.HALF_UP)
							.multiply(new BigDecimal(100));
			model.addAttribute("bdCalc", bdCalc);

			// 收款码
			FindAccountInfoPage findAccountInfoPage = new FindAccountInfoPage();
			AccountInfoDto paramA = new AccountInfoDto();
			paramA.setMbrCode(code);
			findAccountInfoPage.setParam(paramA);
			List<AccountInfoDto> accList = accountInfoService.findAccountInfos(findAccountInfoPage);
			model.addAttribute("accList", accList);

			// 总收入
			AccountDto account = accountService.findAccountByMbrCode(code);
			FindAccWaterPage findAccWaterPage = new FindAccWaterPage();
			AccWaterDto paramAw = new AccWaterDto();
			paramAw.setWaterType(AccWaterType.ADD.getValue());
			paramAw.setStatus(AccWaterStatus.SUCCESS.getValue());
			paramAw.setAccCode(account.getCode());
			paramAw.setBizType(AccWaterBizType.COMMISSION.getValue());
			findAccWaterPage.setParam(paramAw);
			BigDecimal incomeAmt = accWaterService.findIncomeAmt(findAccWaterPage);
			model.addAttribute("incomeAmt", incomeAmt);

			return VIEW;
		}
		return FORM;
	}

	/** 修改 */
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(MemberDto memberDto, RedirectAttributes redirectAttributes) {
		memberService.updateMember(memberDto);
		addMessage(redirectAttributes, "修改客户'" + memberDto.getName() + "'成功");
		return "redirect:" + adminPath + "/member/member/";
	}

	/** 正常/注销/冻结 */
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "/status")
	public String status(MemberDto memberDto, RedirectAttributes redirectAttributes) {
		if (MemberStatus.NORMAL.getValue().equals(memberDto.getStatus())) {
			addMessage(redirectAttributes, "激活成功");
		} else if (MemberStatus.CANCEL.getValue().equals(memberDto.getStatus())) {
			addMessage(redirectAttributes, "注销客户成功");
		} else if (MemberStatus.FREEZE.getValue().equals(memberDto.getStatus())) {
			addMessage(redirectAttributes, "冻结客户成功");
		}
		memberService.updateMember(memberDto);
		return "redirect:" + adminPath + "/member/member/";
	}

	/**
	 * 
	 *
	 * 方法说明：导入客户数据
	 *
	 * @param file
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 林进权 CreateDate: 2017年9月2日
	 *
	 */
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importExcel(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (null == UserUtils.getUser().getMerchant()) {
			addMessage(redirectAttributes, "导入客户失败！失败信息：只支持商户导入会员");
			return "redirect:" + Global.getAdminPath() + "/member/member/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);

			List<ShopMemberDto> list = ei.getDataList(ShopMemberDto.class);
			logger.info("importExcel>>", list);

			for (ShopMemberDto dto : list) {
				try {
					logger.info("excel dto >>" + dto.toString());
					/* 处理excel数据转码 */
//					dto.setMobile(DictUtils.excelChage(dto.getMobile()));

					/* 校验手机号格式 */
					if (!Validator.isMobile(dto.getMobile())) {
						failureMsg.append("<br/>客户手机号： " + dto.getMobile() + "格式不正确; ");
						failureNum++;
						continue;
					}

					if (checkMobile(dto.getMobile())) {
						createMemberOrderInfo(dto);
						successNum++;
					} else {
						failureMsg.append("<br/>手机号 " + dto.getMobile() + " 已存在; ");
						failureNum++;
					}
				} catch (Exception ex) {
					log.error("导入店主数据异常。", ex);
					failureMsg.append("<br/>手机号 " + dto.getMobile() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条客户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条客户" + failureMsg);
		} catch (Exception e) {

			addMessage(redirectAttributes, "导入客户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/member/member/?repage";
	}

	private void createMemberOrderInfo(ShopMemberDto shopMemberDto) {
		MemberDto memberDto = new MemberDto();
		memberDto.setPhone(shopMemberDto.getMobile());
		memberDto.setName(shopMemberDto.getMemberName());
		memberDto.setStatus(MemberStatus.NORMAL.getValue());
		memberDto.setType(MemberType.SHOP.getValue());
		memberDto.setMerchantCode(UserUtils.getUser().getMerchant().getCode());// 这里设置用户所属商户
		memberDto.setPassword(MD5.encryptByMD5("123456"));
		memberService.addMember(memberDto);
	}

	/**
	 * 
	 *
	 * 方法说明：店主会员导入模板下载
	 *
	 * @param response
	 * @param request
	 * @param redirectAttributes
	 * @return
	 *
	 * @author 林进权 CreateDate: 2017年9月2日
	 *
	 */
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "导入模版.xlsx";
			List<ShopMemberDto> list = Lists.newArrayList();
			ShopMemberDto dto = new ShopMemberDto();
			dto.setMemberName("小龙");
			dto.setMobile("13888888888");
			list.add(dto);

			dto = new ShopMemberDto();
			dto.setMemberName("小强");
			dto.setMobile("13888888887");
			list.add(dto);

			new ExportExcel("导入模版", ShopMemberDto.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "店主会员导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/member/?repage";
	}

	/***
	 * 方法说明：检测微信号是否唯一
	 *
	 * @param wxNo
	 * @return
	 *
	 * @author lhy 2017年9月21日
	 *
	 */
	private boolean checkWxNo(String wxNo) {
		FindMemberPage findMemberPage = new FindMemberPage();
		MemberDto param = new MemberDto();
		param.setWxNoAll(wxNo);
		findMemberPage.setParam(param);
		List<MemberDto> list = memberService.findMembers(findMemberPage);
		if (null == list || list.size() == 0) {
			return true;
		}
		return false;
	}

	private boolean checkMobile(String mobile) {
		FindMemberPage findMemberPage = new FindMemberPage();
		MemberDto param = new MemberDto();
		param.setPhone(mobile);
		findMemberPage.setParam(param);
		List<MemberDto> list = memberService.findMembers(findMemberPage);
		if (null == list || list.size() == 0) {
			return true;
		}
		return false;
	}
}
