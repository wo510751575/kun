package com.lj.eshop.eis.controller.prodcut;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lj.eshop.dto.FindProTexturePage;
import com.lj.eshop.dto.ProductTextureDto;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.emus.Status;
import com.lj.eshop.service.IProductTextureService;

/**
 * 
 * 
 * 类说明:商品材质 
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 深圳扬恩科技有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2017年9月2日
 */
@RestController
@RequestMapping("/product")
public class TextureController extends BaseController {
	@Autowired
	private IProductTextureService productTextureService;
	@RequestMapping(value = "/textureList")
	public ResponseDto textureList() {
		ProductTextureDto productDto = new ProductTextureDto();
		FindProTexturePage findProductTexturePage = new FindProTexturePage();
		try {
			productDto.setStatus(Status.USE.getValue());
			findProductTexturePage.setParam(productDto);
			List<ProductTextureDto> list = productTextureService.findProTextures(findProductTexturePage);
			logger.info("get(return ={})", list);
			return ResponseDto.successResp(list);
		} catch (Exception e) {
			return ResponseDto.generateFailureResponse(e);
		}
	}
}
