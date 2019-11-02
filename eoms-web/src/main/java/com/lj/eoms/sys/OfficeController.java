package com.lj.eoms.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ape.common.config.Global;
import com.ape.common.utils.StringUtils;
import com.ape.common.web.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lj.eoms.entity.sys.Office;
import com.lj.eoms.entity.sys.User;
import com.lj.eoms.service.sys.OfficeService;
import com.lj.eoms.utils.DictUtils;
import com.lj.eoms.utils.UserUtils;
import com.lj.eshop.dto.MerchantDto;
import com.lj.eshop.service.IMerchantService;

/**
 * 机构Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	@Autowired
	private IMerchantService merchantService;// 电商商户信息 2017-8-25 hy

	@ModelAttribute("office")
	public Office get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return officeService.get(id);
		} else {
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = { "" })
	public String index(Office office, Model model) {
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = { "list" })
	public String list(Office office, Model model) {
//		if(office==null){
//			office = UserUtils.getUser().getOffice();
//		}
		model.addAttribute("list", officeService.findList(office));
		return "modules/sys/officeList";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
		User user = UserUtils.getUser();
		if (office.getParent() == null || office.getParent().getId() == null) {
			office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea() == null) {
			office.setArea(user.getOffice().getArea());
		}
		// 自动获取排序号
		if (StringUtils.isBlank(office.getId()) && office.getParent() != null) {
			int size = 0;
			List<Office> list = officeService.findAll();
			for (int i = 0; i < list.size(); i++) {
				Office e = list.get(i);
				if (e.getParent() != null && e.getParent().getId() != null
						&& e.getParent().getId().equals(office.getParent().getId())) {
					size++;
				}
			}
			office.setCode(office.getParent().getCode()
					+ StringUtils.leftPad(String.valueOf(size > 0 ? size + 1 : 1), 3, "0"));
		}
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, office)) {
			return form(office, model);
		}
		officeService.save(office);
		// 如果是公司则给其在电商平台开一个商户 2017-8-25 by hy
		if ("1".equals(office.getType()) && office.getParent().getId() != null
				&& "1".equals(office.getParent().getId())) {
			// 1.电商商户
			MerchantDto merchantDto = new MerchantDto();
			merchantDto.setCode(office.getId());
			merchantDto.setCreateTime(new Date());
			merchantDto.setMerchantAddr(office.getAddress());
			merchantDto.setMerchantName(office.getName());
			merchantDto.setMerchantPhone(office.getPhone());
			merchantDto.setOfficeId(office.getId());// 用户公司的ID和商电商平台的商户信息作关联
			// 2.会员体系的商户
			merchantService.addMerchant(merchantDto);
			/*
			 * AddMerchant addMerchant=new AddMerchant();
			 * addMerchant.setMerchantNo(office.getId());//兼容OMS同步数据
			 * addMerchant.setMerchantName(office.getName());
			 * addMerchant.setStatus(Status.NORMAL);
			 * addMerchant.setAddress(office.getAddress());
			 * addMerchant.setEmail(office.getEmail()); //
			 * addMerchant.setBusinessType(office.getBusinessType());行业，无数据不填
			 * addMerchant.setLogoAddr(office.getLogo()); //
			 * addMerchant.setWebsiteUrl(office.);网址，无数据不填
			 * addMerchant.setTelephone(office.getPhone());
			 * addMerchant.setCreateId(UserUtils.getUser().getId()); //
			 * addMerchant.setRemark(office.getRemark());备注，无数据不填
			 * mbrMerchantService.addMerchant_ec(addMerchant);
			 */
		} // end

		if (office.getChildDeptList() != null) {
			Office childOffice = null;
			for (String id : office.getChildDeptList()) {
				childOffice = new Office();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType("2");
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade()) + 1));
				childOffice.setUseable(Global.YES);
				officeService.save(childOffice);
			}
		}

		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?id=" + id + "&parentIds=" + office.getParentIds();
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		officeService.delete(office);
		addMessage(redirectAttributes, "删除机构成功");
		return "redirect:" + adminPath + "/sys/office/list?id=" + office.getParentId() + "&parentIds="
				+ office.getParentIds();
	}

	/**
	 * 获取机构JSON数据。
	 * 
	 * @param extId    排除的ID
	 * @param type     类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade    显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.findList(isAll);
		for (int i = 0; i < list.size(); i++) {
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId)
					|| (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && "3".equals(type)) {
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
}
