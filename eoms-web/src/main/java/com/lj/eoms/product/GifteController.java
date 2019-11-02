/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eoms.product;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.web.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.eoms.utils.UserUtils;
import com.lj.eshop.dto.FindProductGiftPage;
import com.lj.eshop.dto.ProductGiftDto;
import com.lj.eshop.emus.MemberRankGift;
import com.lj.eshop.emus.Status;
import com.lj.eshop.service.IProductGiftService;

/**
 * 
 * 类说明：系统会员礼品管理。
 * <p>
 * 详细描述：
 * 
 * @Company: 
 * @author lhy
 * 
 *         CreateDate: 2017年8月26日
 */
@Controller
@RequestMapping("${adminPath}/product/gift/")
public class GifteController extends BaseController {

	public static final String LIST = "modules/product/gift/list";
	public static final String FORM = "modules/product/gift/form";

	@Autowired
	private IProductGiftService productGiftService;

	/** 列表 */
	@RequiresPermissions("product:gift:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindProductGiftPage productGiftPage, Integer pageNo, Integer pageSize, Model model) {
		if (pageNo != null) {
			productGiftPage.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			productGiftPage.setLimit(pageSize);
		}
		Page<ProductGiftDto> pageDto = productGiftService.findProductGiftPage(productGiftPage);
		List<ProductGiftDto> list = Lists.newArrayList();
		list.addAll(pageDto.getRows());

		com.ape.common.persistence.Page<ProductGiftDto> page = new com.ape.common.persistence.Page<ProductGiftDto>(
				pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		page.initialize();

		model.addAttribute("page", page);
		model.addAttribute("statuss", Status.values());
		model.addAttribute("reqParam", productGiftPage);

		return LIST;
	}

	/** 新增 */
	@RequiresPermissions("product:gift:edit")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ProductGiftDto supplyDto, RedirectAttributes redirectAttributes) {
		supplyDto.setCreateTime(new Date());
		supplyDto.setCreater(UserUtils.getUser().getId());// 这里设置用户
		productGiftService.addProductGift(supplyDto);

		addMessage(redirectAttributes, "保存会员礼品'" + supplyDto.getName() + "'成功");
		return "redirect:" + adminPath + "/product/gift/";
	}

	/** 新增/修改表单 */
	@RequiresPermissions("product:gift:view")
	@RequestMapping(value = "/form")
	public String form(String code, Model model) {
		ProductGiftDto param = new ProductGiftDto();
		param.setCode(code);
		ProductGiftDto rtDto = productGiftService.findProductGift(param);
		model.addAttribute("data", rtDto);
		model.addAttribute("statuss", Status.values());
		model.addAttribute("memberRankGifts", MemberRankGift.values());
		return FORM;
	}

	/** 修改 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(ProductGiftDto supplyDto, RedirectAttributes redirectAttributes) {
		productGiftService.updateProductGift(supplyDto);
		addMessage(redirectAttributes, "修改会员礼品'" + supplyDto.getName() + "'成功");
		return "redirect:" + adminPath + "/product/gift/";
	}

	/** 启用/停用 */
	@RequestMapping(value = "/status")
	public String status(ProductGiftDto supplyDto, RedirectAttributes redirectAttributes) {
		if (Status.USE.getValue().equals(supplyDto.getStatus())) {
			productGiftService.updateProductGift(supplyDto);
			addMessage(redirectAttributes, "启用会员礼品成功");
		} else if (Status.STOP.getValue().equals(supplyDto.getStatus())) {
			productGiftService.updateProductGift(supplyDto);
			addMessage(redirectAttributes, "停用会员礼品成功");
		}
		return "redirect:" + adminPath + "/product/gift/";
	}

}
