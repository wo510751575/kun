/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eoms.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ape.common.config.Global;
import com.ape.common.web.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.pagination.PageSortType;
import com.lj.base.mvc.web.httpclient.HttpClientUtils;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.eoms.dto.ShopMemberDto;
import com.lj.eoms.service.AreaHessianService;
import com.lj.eoms.utils.UserUtils;
import com.lj.eoms.utils.Validator;
import com.lj.eoms.utils.excel.ExportExcel;
import com.lj.eoms.utils.excel.ImportExcel;
import com.lj.eshop.dto.FindMemberPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.emus.MemberGrade;
import com.lj.eshop.emus.MemberSex;
import com.lj.eshop.emus.MemberSourceFrom;
import com.lj.eshop.emus.MemberStatus;
import com.lj.eshop.emus.MemberType;
import com.lj.eshop.service.IMemberService;
import com.lj.eshop.service.IPaymentService;
import com.lj.eshop.service.IShopBgImgService;
import com.lj.eshop.service.IShopService;
import com.lj.eshop.service.IShopStyleService;

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
	@Autowired
	private IShopService shopService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private IShopBgImgService shopBgImgService;
	@Autowired
	private IShopStyleService shopStyleService;
	@Autowired
	private AreaHessianService areaService;
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	/** 列表 */
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindMemberPage memberPage, Integer pageNo, Integer pageSize, Model model) {
		MemberDto member = memberPage.getParam();
		if (member == null) {
			member = new MemberDto();
		}

		if (null != UserUtils.getUser().getMerchant()) {
			member.setMerchantCode(UserUtils.getUser().getMerchant().getCode());// 这里设置用户所属商户
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
			return VIEW;
		}
		return FORM;
	}

	/** 修改 */
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(MemberDto memberDto, RedirectAttributes redirectAttributes) {

		// 把会员信息同步更新到热文会员
		String url = localCacheSystemParams.getSystemParam("cc", "rw", "rwRegistUrl");
		Map<String, String> map = new HashMap<>();
		map.put("code", memberDto.getCode());
		map.put("name", memberDto.getName());
		map.put("phone", memberDto.getPhone() == null ? memberDto.getCode() : memberDto.getPhone());
		String result = HttpClientUtils.postToWeb(url, map);
		if (com.lj.base.core.util.StringUtils.isNotEmpty(result)) {
			JSONObject obj = (JSONObject) JSON.parse(result);
			String rs = (String) obj.get("returnObject");
			if (!"OK".equalsIgnoreCase(rs)) {
				addMessage(redirectAttributes, "同步客户'" + memberDto.getName() + "'到热文失败");
				return "redirect:" + adminPath + "/member/member/";
			}
		} else {
			addMessage(redirectAttributes, "同步客户'" + memberDto.getName() + "'到热文失败");
			return "redirect:" + adminPath + "/member/member/";
		}
		addMessage(redirectAttributes, "修改客户'" + memberDto.getName() + "'成功");
		return "redirect:" + adminPath + "/member/member/";
	}

	/** 正常/注销/冻结 */
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "/status")
	public String status(MemberDto memberDto, RedirectAttributes redirectAttributes) {
		if (MemberStatus.NORMAL.getValue().equals(memberDto.getStatus())) {
			addMessage(redirectAttributes, "取消冻结客户成功");
		} else if (MemberStatus.CANCEL.getValue().equals(memberDto.getStatus())) {
			addMessage(redirectAttributes, "注销客户成功");
		} else if (MemberStatus.FREEZE.getValue().equals(memberDto.getStatus())) {
			addMessage(redirectAttributes, "冻结客户成功");
		}
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
