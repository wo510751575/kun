package com.lj.eoms.product;

import java.util.Date;
import java.util.List;

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
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.eoms.dto.ResponseDto;
import com.lj.eoms.utils.UserUtils;
import com.lj.eshop.dto.CatalogDto;
import com.lj.eshop.dto.FindProductPage;
import com.lj.eshop.dto.FindProductPromotePage;
import com.lj.eshop.dto.FindProductSkuPage;
import com.lj.eshop.dto.FindSupplyPage;
import com.lj.eshop.dto.ProductDto;
import com.lj.eshop.dto.ProductPromoteDto;
import com.lj.eshop.dto.ProductSkuDto;
import com.lj.eshop.dto.SupplyDto;
import com.lj.eshop.emus.DelFlag;
import com.lj.eshop.emus.ProductStatus;
import com.lj.eshop.emus.ProductYN;
import com.lj.eshop.emus.Status;
import com.lj.eshop.service.ICatalogService;
import com.lj.eshop.service.IProductPromoteService;
import com.lj.eshop.service.IProductService;
import com.lj.eshop.service.IProductSkuService;


/**
 * 
 * 类说明：系统商品材质管理。
 * <p>
 * 详细描述：
 * 
 * @Company: 小坤有限公司
 * @author lhy
 * 
 *         CreateDate: 2017年8月26日
 */
@Controller
@RequestMapping("${adminPath}/product/promote/")
public class ProductPromoteController extends BaseController {

	public static final String LIST = "modules/product/promote/list";
	public static final String FORM = "modules/product/promote/form";
	public static final String TAGLIST = "modules/product/promote/ftablelist";
	@Autowired
	private IProductPromoteService promoteService;

	@Autowired
	private IProductSkuService productSkuService;// 商品SKU
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICatalogService catalogService;
	
	/** 列表 */
	@RequiresPermissions("product:promote:view")
	@RequestMapping(value = { "list", "" })
	public String list(FindProductPromotePage promotePage, Integer pageNo, Integer pageSize, Model model) {
		if (pageNo != null) {
			promotePage.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			promotePage.setLimit(pageSize);
		}
		Page<ProductPromoteDto> pageDto = promoteService.findProductPromotePage(promotePage);
		List<ProductPromoteDto> list = Lists.newArrayList();
		list.addAll(pageDto.getRows());

		com.ape.common.persistence.Page<ProductPromoteDto> page = new com.ape.common.persistence.Page<ProductPromoteDto>(
				pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
		page.initialize();
		
		model.addAttribute("page", page);
		model.addAttribute("statuss", Status.values());
		model.addAttribute("reqParam", promotePage);

		return LIST;
	}

	/** 新增 */
	@RequiresPermissions("product:promote:edit")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ProductPromoteDto promoteDto, RedirectAttributes redirectAttributes) {
		promoteDto.setCode(GUID.generateCode());
		promoteDto.setCreateTime(new Date());
		promoteDto.setCreater(UserUtils.getUser().getId());// 这里设置用户
		promoteDto.setUpdater(UserUtils.getUser().getId());
		promoteDto.setUpdateTime(new Date());
		promoteService.addProductPromote(promoteDto);
		
		//获取当前的product中对应的skus信息
		FindProductSkuPage sku = new FindProductSkuPage();
		ProductSkuDto psku = new ProductSkuDto();
		psku.setProductCode(promoteDto.getProductCode());
		/*	psku.setDelFlag(DelFlag.N.getValue());*/
		sku.setParam(psku);
		List<ProductSkuDto> productSkus = productSkuService.findProductSkus(sku);
		//根据skus数组中code更新 更新sku相关信息
		if (promoteDto.getSkus() != null) {
			int size = promoteDto.getSkus().size();
			for (int i = 0; i < size; i++) {
				ProductSkuDto oldsku = productSkus.get(i);
				ProductSkuDto newsku = promoteDto.getSkus().get(i);
				if(oldsku.getCode().equals(newsku.getCode())) {
					oldsku.setOrgPrice(newsku.getOrgPrice());
				}
				// 不更新无关的信息
				oldsku.setRankPriceDtos(null);
				productSkuService.updateProductSku(oldsku);
			}
		}
		addMessage(redirectAttributes, "保存成功" );
		return "redirect:" + adminPath + "/product/promote/";
	}

	/** 新增/修改表单 */
	@RequiresPermissions("product:promote:view")
	@RequestMapping(value = "/form")
	public String form(String code, Model model,String productCode) {
		ProductPromoteDto param = new ProductPromoteDto();
		param.setCode(code);
		ProductPromoteDto rtDto = promoteService.findProductPromote(param);
		model.addAttribute("data", rtDto);
 		model.addAttribute("statuss", Status.values());
 		if(productCode != null) {
 			FindProductPromotePage promotePage = new FindProductPromotePage();
 			promotePage.setParam(rtDto);
 			Page<ProductPromoteDto> pageDto = promoteService.findProductPromotePage(promotePage);
 			List<ProductPromoteDto> list = Lists.newArrayList();
 			list.addAll(pageDto.getRows());
 			model.addAttribute("data",list.get(0));
 			// 商品SKU
			FindProductSkuPage sku = new FindProductSkuPage();
			ProductSkuDto psku = new ProductSkuDto();
			psku.setProductCode(rtDto.getProductCode());
			psku.setDelFlag(DelFlag.N.getValue());
			sku.setParam(psku);
			List<ProductSkuDto> productSkus = productSkuService.findProductSkus(sku);
 			model.addAttribute("sku",productSkus);
 		}
 		
 		//款号分页查询产品
 		FindProductPromotePage promotePage = new FindProductPromotePage();
 		promotePage.setStart(0);
		promotePage.setLimit(10);
	
		Page<ProductPromoteDto> pageDto = promoteService.findProductPromotePage(promotePage);
		List<ProductPromoteDto> list = Lists.newArrayList();
		list.addAll(pageDto.getRows());

		com.ape.common.persistence.Page<ProductPromoteDto> page = new com.ape.common.persistence.Page<ProductPromoteDto>(1, pageDto.getLimit(),pageDto.getTotal(), list);
		page.initialize();
		model.addAttribute("page", page);
		model.addAttribute("prodcutStatuss", ProductStatus.values());
		return FORM;
	}

	/** 修改 */
	@RequiresPermissions("product:promote:edit")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(ProductPromoteDto promoteDto, RedirectAttributes redirectAttributes) {
		ProductPromoteDto param = new ProductPromoteDto();
		param.setCode(promoteDto.getCode());
		param.setProductCode(promoteDto.getProductCode());
		ProductPromoteDto rtDto = promoteService.findProductPromote(param);
		promoteDto.setUpdater(UserUtils.getUser().getId());
		promoteDto.setUpdateTime(new Date());
		promoteDto.setCreater(rtDto.getCreater());
		promoteDto.setCreateTime(rtDto.getCreateTime());
		promoteService.updateProductPromote(promoteDto);
		
		//获取当前的product中对应的skus信息
		FindProductSkuPage sku = new FindProductSkuPage();
		ProductSkuDto psku = new ProductSkuDto();
		psku.setProductCode(promoteDto.getProductCode());
		sku.setParam(psku);
		List<ProductSkuDto> productSkus = productSkuService.findProductSkus(sku);
		//根据skus数组中code更新 更新sku相关信息
		if (promoteDto.getSkus() != null) {
			int size = promoteDto.getSkus().size();
			for (int i = 0; i < size; i++) {
				ProductSkuDto oldsku = productSkus.get(i);
				ProductSkuDto newsku = promoteDto.getSkus().get(i);
				if(oldsku.getCode().equals(newsku.getCode())) {
					oldsku.setOrgPrice(newsku.getOrgPrice());
				}
				// 不更新无关的信息
				oldsku.setRankPriceDtos(null);
				productSkuService.updateProductSku(oldsku);
			}
		}
		addMessage(redirectAttributes, "修改每日一抢商品成功");
		return "redirect:" + adminPath + "/product/promote/";
	}

	/** 启用/停用 */
	@RequiresPermissions("product:promote:edit")
	@RequestMapping(value = "/status")
	public String status(ProductPromoteDto promoteDto, RedirectAttributes redirectAttributes) {
		if (Status.USE.getValue().equals(promoteDto.getStatus())) {
			promoteService.updateProductPromote(promoteDto);
			addMessage(redirectAttributes, "启用成功");
		} else if (Status.STOP.getValue().equals(promoteDto.getStatus())) {
			promoteService.updateProductPromote(promoteDto);
			addMessage(redirectAttributes, "停用成功");
		}
		return "redirect:" + adminPath + "/product/promote/";
	}
	
	/**
	 * 
	 * 方法说明：查询款号
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月14日
	 *
	 */
	@RequestMapping(value={"view"})
	public String view(FindProductPage productPage, Integer pageNo, Integer pageSize, Model model){		
		ProductDto productDto = productPage.getParam();
		if (productDto == null) {
			productDto = new ProductDto();
		}

		if (null != UserUtils.getUser().getMerchant()) {

			productDto.setMerchantCode(UserUtils.getUser().getMerchant().getCode());// 这里设置用户所属商户
			productPage.setParam(productDto);
			if (pageNo != null) {
				productPage.setStart((pageNo - 1) * pageSize);
			}
			if (pageSize != null) {
				productPage.setLimit(pageSize);
			}
			Page<ProductDto> pageDto = productService.findProductPage(productPage);
			List<ProductDto> list = Lists.newArrayList();
			list.addAll(pageDto.getRows());

			com.ape.common.persistence.Page<ProductDto> page = new com.ape.common.persistence.Page<ProductDto>(
					pageNo == null ? 1 : pageNo, pageDto.getLimit(), pageDto.getTotal(), list);
			page.initialize();

			if (!StringUtils.isEmpty(productDto.getCatalogTypeCode())) {
				CatalogDto cp = new CatalogDto();
				cp.setCode(productDto.getCatalogTypeCode());
				CatalogDto productCatalog = catalogService.findCatalog(cp);
				model.addAttribute("productCatalog", productCatalog);// 查询的类型
			}
			// 供应商
			SupplyDto supplyDto = new SupplyDto();
			supplyDto.setStatus(Status.USE.getValue());
			supplyDto.setMerchantCode(UserUtils.getUser().getMerchant().getCode());// 这里设置用户所属商户
			FindSupplyPage supplyPage = new FindSupplyPage();
			supplyPage.setParam(supplyDto);

			model.addAttribute("page", page);
			model.addAttribute("prodcutStatuss", ProductStatus.values());
			model.addAttribute("productYNs", ProductYN.values());
			model.addAttribute("reqParam", productPage);
		}

		return TAGLIST;
	}
	
	/**
	 * 
	 * 方法说明：根据款号查询商品sku信息
	 *
	 * @param model
	 * @param code
	 * @return
	 *
	 * @author 彭俊霖 CreateDate: 2017年11月14日
	 *
	 */
	@RequiresPermissions("product:promote:edit")
	@RequestMapping(value = "/sku/list")
	@ResponseBody
	public ResponseDto editSku(String modelNum, Model model,String productCode) {
		//获取产品信息
		ProductDto dto = new ProductDto();
		dto.setCode(productCode);
		ProductDto pDto = productService.findProduct(dto);

		// 商品SKU
		FindProductSkuPage sku = new FindProductSkuPage();
		ProductSkuDto psku = new ProductSkuDto();
		psku.setProductCode(productCode);
		psku.setDelFlag(DelFlag.N.getValue());
		sku.setParam(psku);
		List<ProductSkuDto> productSkus = productSkuService.findProductSkus(sku);
		ProductPromoteDto rtDto = new ProductPromoteDto();
		rtDto.setSkus(productSkus);
		rtDto.setProductDto(pDto);
		return ResponseDto.successResp(rtDto);
	}
}


