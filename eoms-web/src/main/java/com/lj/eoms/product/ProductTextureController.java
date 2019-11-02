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
import com.lj.eshop.dto.FindProTexturePage;
import com.lj.eshop.dto.ProductTextureDto;
import com.lj.eshop.emus.Status;
import com.lj.eshop.service.IProductTextureService;


/**
 * 
 * 类说明：系统商品材质管理。
 * <p>
 * 详细描述：
 * 
 * @Company: 
 * @author lhy
 * 
 *         CreateDate: 2017年8月26日
 */
@Controller
@RequestMapping("${adminPath}/product/producttexture/")
public class ProductTextureController extends BaseController {

	public static final String LIST = "modules/product/producttexture/list";
	public static final String FORM = "modules/product/producttexture/form";

	@Autowired
	private IProductTextureService proTextureService;

	/** 列表 */
	@RequiresPermissions("product:producttexture:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindProTexturePage proTexturePage, Integer pageNo, Integer pageSize, Model model) {
		if (pageNo != null) {
			proTexturePage.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			proTexturePage.setLimit(pageSize);
		}
		Page<ProductTextureDto> pageDto = proTextureService.findProTexturePage(proTexturePage);
		List<ProductTextureDto> list = Lists.newArrayList();
		list.addAll(pageDto.getRows());

		com.ape.common.persistence.Page<ProductTextureDto> page = new com.ape.common.persistence.Page<ProductTextureDto>(
				pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		page.initialize();

		model.addAttribute("page", page);
		model.addAttribute("statuss", Status.values());
		model.addAttribute("reqParam", proTexturePage);

		return LIST;
	}

	/** 新增 */
	@RequiresPermissions("product:producttexture:edit")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ProductTextureDto supplyDto, RedirectAttributes redirectAttributes) {
		supplyDto.setCreateTime(new Date());
		supplyDto.setCreater(UserUtils.getUser().getId());// 这里设置用户
		proTextureService.addProductTexture(supplyDto);

		addMessage(redirectAttributes, "保存商品材质'" + supplyDto.getTextureName() + "'成功");
		return "redirect:" + adminPath + "/product/producttexture/";
	}

	/** 新增/修改表单 */
	@RequiresPermissions("product:producttexture:view")
	@RequestMapping(value = "/form")
	public String form(String code, Model model) {
		ProductTextureDto param = new ProductTextureDto();
		param.setCode(code);
		ProductTextureDto rtDto = proTextureService.findProTexture(param);
		model.addAttribute("data", rtDto);
		model.addAttribute("statuss", Status.values());
		return FORM;
	}

	/** 修改 */
	@RequiresPermissions("product:producttexture:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(ProductTextureDto supplyDto, RedirectAttributes redirectAttributes) {
		proTextureService.updateProTexture(supplyDto); 
		addMessage(redirectAttributes, "修改商品材质'" + supplyDto.getTextureName() + "'成功");
		return "redirect:" + adminPath + "/product/producttexture/";
	}

	/** 启用/停用 */
	@RequiresPermissions("product:producttexture:edit")
	@RequestMapping(value = "/status")
	public String status(ProductTextureDto supplyDto, RedirectAttributes redirectAttributes) {
		if (Status.USE.getValue().equals(supplyDto.getStatus())) {
			proTextureService.updateProTexture(supplyDto);
			addMessage(redirectAttributes, "启用商品规格成功");
		} else if (Status.STOP.getValue().equals(supplyDto.getStatus())) {
			proTextureService.updateProTexture(supplyDto);
			addMessage(redirectAttributes, "停用商品规格成功");
		}
		return "redirect:" + adminPath + "/product/producttexture/";
	}
}