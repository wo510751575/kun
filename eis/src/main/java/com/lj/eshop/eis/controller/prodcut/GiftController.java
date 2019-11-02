package com.lj.eshop.eis.controller.prodcut;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.util.AssertUtils;
import com.lj.eshop.dto.FindProductGiftPage;
import com.lj.eshop.dto.ProductGiftDto;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.emus.Status;
import com.lj.eshop.service.IProductGiftService;

/**
 * 
 * 
 * 类说明：会员礼包
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 小坤有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2017年9月2日
 */
@RestController
@RequestMapping("/product/gift")
public class GiftController extends BaseController {

	@Autowired
	private IProductGiftService productGiftService;

	@RequestMapping(value = { "get" })
	public ResponseDto get(String giftCode, Integer pageNo, Integer pageSize) {
		logger.info("list(String giftCode={}, Integer pageNo={}, Integer pageSize={})", giftCode, pageNo, pageSize);
		AssertUtils.notNullAndEmpty(giftCode, "礼包编号不能为空");
		ProductGiftDto productDto = new ProductGiftDto();
		FindProductGiftPage findProductGiftPage = new FindProductGiftPage();
		if (pageNo != null) {
			findProductGiftPage.setStart((pageNo - 1) * pageSize);
		}
		if (pageSize != null) {
			findProductGiftPage.setLimit(pageSize);
		}
		try {
			productDto.setGiftCode(giftCode);
			productDto.setStatus(Status.USE.getValue());
			findProductGiftPage.setParam(productDto);
			List<ProductGiftDto> list = productGiftService.findProductGifts(findProductGiftPage);
			logger.info("get(return ={})", list);
			return ResponseDto.successResp(list);
		} catch (Exception e) {
			return ResponseDto.generateFailureResponse(e);
		}
	}
}
