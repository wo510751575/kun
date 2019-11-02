package com.lj.eshop.service.impl;

import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.eshop.dto.FindProductGiftPage;
import com.lj.eshop.dto.ProductGiftDto;
import com.lj.eshop.emus.Status;
import com.lj.eshop.service.IProductGiftService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 *         CreateDate: 2017.12.11
 */
public class ProductGiftServiceImplTest extends SpringTestCase {

	@Resource
	IProductGiftService productGiftService;

	/**
	 * 
	 *
	 * 方法说明：添加赠品礼包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addProductGift() throws TsfaServiceException {
		ProductGiftDto productGiftDto = new ProductGiftDto();
		// add数据录入
		productGiftDto.setCode("Code");
		productGiftDto.setGiftCode("GiftCode");
		productGiftDto.setGiftName("GiftName");
		productGiftDto.setName("Name");
		productGiftDto.setStatus(Status.USE.getValue());
		productGiftDto.setCreater("Creater");

		productGiftService.addProductGift(productGiftDto);

	}

	/**
	 * 
	 *
	 * 方法说明：修改赠品礼包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateProductGift() throws TsfaServiceException {
		ProductGiftDto productGiftDto = new ProductGiftDto();
		// update数据录入
		productGiftDto.setCode("Code");
		productGiftDto.setGiftCode("GiftCode");
		productGiftDto.setGiftName("GiftName");
		productGiftDto.setName("Name");
		productGiftDto.setStatus("Status");
		productGiftDto.setCreater("Creater");
		productGiftService.updateProductGift(productGiftDto);

	}

	/**
	 * 
	 *
	 * 方法说明：查找赠品礼包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findProductGift() throws TsfaServiceException {
		ProductGiftDto findProductGift = new ProductGiftDto();
		findProductGift.setCode("111");
		productGiftService.findProductGift(findProductGift);
	}

	/**
	 * 
	 *
	 * 方法说明：查找赠品礼包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findProductGiftPage() throws TsfaServiceException {
		FindProductGiftPage findProductGiftPage = new FindProductGiftPage();
		Page<ProductGiftDto> page = productGiftService.findProductGiftPage(findProductGiftPage);
		Assert.assertNotNull(page);

	}

	/**
	 * 
	 *
	 * 方法说明：查找赠品礼包信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findProductGifts() throws TsfaServiceException {
		FindProductGiftPage findProductGiftPage = new FindProductGiftPage();
		List<ProductGiftDto> page = productGiftService.findProductGifts(findProductGiftPage);
		Assert.assertNotNull(page);

	}

}
