package com.lj.eoms.marketing;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.web.BaseController;
import com.google.common.collect.Lists;
import com.lj.base.core.pagination.Page;
import com.lj.eoms.utils.UserUtils;
import com.lj.eshop.dto.cm.AddMaterialType;
import com.lj.eshop.dto.cm.FindMaterialType;
import com.lj.eshop.dto.cm.FindMaterialTypePage;
import com.lj.eshop.dto.cm.FindMaterialTypePageReturn;
import com.lj.eshop.dto.cm.FindMaterialTypeReturn;
import com.lj.eshop.dto.cm.UpdateMaterialType;
import com.lj.eshop.emus.cm.MaterialDimensionStatus;
import com.lj.eshop.service.cm.IMaterialTypeService;

/**
 * 
 * 
 * 类说明：素材类型服务类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 
 * @author 林进权
 * 
 *         CreateDate: 2017年7月14日
 */
@Controller
@RequestMapping(value = "${adminPath}/marketing/materialtype/")
public class MaterialTypeController extends BaseController {

	/**
	 * 素材类型服务
	 */
	@Resource
	private IMaterialTypeService materialtypeService;

	/**
	 * 
	 *
	 * 方法说明：进行分页数据转换 素材类型表
	 * 
	 * @param model
	 * @return 返回分页数据，OMS进行数据展现
	 *
	 * @author 林进权 CreateDate: 2017年9月15日
	 *
	 */

	@RequestMapping(value = { "list", "" })
	public String list(Model model, Integer pageNo, Integer pageSize, FindMaterialTypePage findMaterialTypePage) {
		try {
			if (pageNo != null) {
				findMaterialTypePage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				findMaterialTypePage.setLimit(pageSize);
			}

			findMaterialTypePage.setMerchantNo(UserUtils.getUser().getMerchant().getOfficeId());
			Page<FindMaterialTypePageReturn> pages = materialtypeService.findMaterialTypePage(findMaterialTypePage);
			List<FindMaterialTypePageReturn> list = Lists.newArrayList();
			list.addAll(pages.getRows());
			com.ape.common.persistence.Page<FindMaterialTypePageReturn> page = new com.ape.common.persistence.Page<FindMaterialTypePageReturn>(
					pageNo == null ? 1 : pageNo, pages.getLimit(), pages.getTotal(), list);
			page.initialize();
			model.addAttribute("materialDimensionStatuss", MaterialDimensionStatus.values());
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/marketing/materialtype/materialtypeList";
	}

	/**
	 * 
	 *
	 * 方法说明：素材类型编辑页面数据
	 *
	 * @param findMaterialType
	 * @param model
	 * @return 返回编辑页面数据
	 *
	 * @author 林进权 CreateDate: 2017年9月15日
	 *
	 */
	@RequestMapping(value = "form")
	public String form(FindMaterialType findMaterialType, Model model) {
		try {
			if (findMaterialType != null && findMaterialType.getCode() != null) {
				FindMaterialTypeReturn FindMaterialReturn = materialtypeService.findMaterialType(findMaterialType);
				model.addAttribute("data", FindMaterialReturn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modules/marketing/materialtype/materialtypeForm";
	}

	/**
	 * 
	 *
	 * 方法说明：新增 素材类型数据保存方法
	 *
	 * @param addMaterialType
	 * @param model
	 * @param redirectAttributes
	 * @return 保存成功后跳转页面
	 * 
	 * @author 林进权 CreateDate: 2017年9月15日
	 *
	 */
	@RequestMapping(value = "save")
	public String save(AddMaterialType addMaterialType, Model model, RedirectAttributes redirectAttributes) {
		try {
			addMaterialType.setMaterialDimension(MaterialDimensionStatus.MERCHANT.getValue());
			addMaterialType.setMerchantNo(UserUtils.getUser().getMerchant().getOfficeId());
			addMaterialType.setCreateId(UserUtils.getUser().getName());
			materialtypeService.addMaterialType(addMaterialType);
			addMessage(redirectAttributes, "保存素材'" + addMaterialType.getTypeName() + "'成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + adminPath + "/marketing/materialtype/";
	}

	/**
	 * 
	 *
	 * 方法说明：素材类型编辑修改
	 * 
	 * @param updateMaterialType
	 * @param model
	 * @param redirectAttributes
	 * @return 保存数据成功后跳转页面
	 *
	 * @author 林进权 CreateDate: 2017年9月15日
	 *
	 */
	@RequestMapping(value = "edit")
	public String edit(UpdateMaterialType updateMaterialType, Model model, RedirectAttributes redirectAttributes) {
		try {

			materialtypeService.updateMaterialType(updateMaterialType);
			addMessage(redirectAttributes, "保存素材'" + updateMaterialType.getTypeName() + "'成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + adminPath + "/marketing/materialtype/";
	}

}
