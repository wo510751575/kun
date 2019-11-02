package com.lj.eshop.service.impl;
//package com.lj.eshop.test;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.List;

import com.lj.eshop.dto.ProductPromoteDto;
import com.lj.eshop.emus.Status;
import com.lj.eshop.dto.FindProductPromotePage;
import com.lj.eshop.service.IProductPromoteService;
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
 * CreateDate: 2017.12.11
 */
public class ProductPromoteServiceImplTest extends SpringTestCase{

	@Resource
	IProductPromoteService productPromoteService;



	/**
	 * 
	 *
	 * 方法说明：添加商品每日一抢信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addProductPromote() throws TsfaServiceException{
		ProductPromoteDto productPromoteDto = new ProductPromoteDto();
		//add数据录入
		productPromoteDto.setCode("Code");
		productPromoteDto.setProductCode("ProductCode");
		productPromoteDto.setOpenDate(new Date());
		productPromoteDto.setCloseDate(new Date());
		productPromoteDto.setStatus(Status.USE.getValue());
		productPromoteDto.setCreater("Creater");
		productPromoteDto.setCreateTime(new Date());
		productPromoteDto.setUpdater("Updater");
		productPromoteDto.setUpdateTime(new Date());
		
		productPromoteService.addProductPromote(productPromoteDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改商品每日一抢信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateProductPromote() throws TsfaServiceException{
		ProductPromoteDto productPromoteDto = new ProductPromoteDto();
		//update数据录入
		productPromoteDto.setCode("Code");
		productPromoteDto.setProductCode("ProductCode");
		productPromoteDto.setOpenDate(new Date());
		productPromoteDto.setCloseDate(new Date());
		productPromoteDto.setStatus(Status.USE.getValue());
		productPromoteDto.setCreater("Creater");
		productPromoteDto.setCreateTime(new Date());
		productPromoteDto.setUpdater("Updater");
		productPromoteDto.setUpdateTime(new Date());

		productPromoteService.updateProductPromote(productPromoteDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商品每日一抢信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findProductPromote() throws TsfaServiceException{
		ProductPromoteDto findProductPromote = new ProductPromoteDto();
		findProductPromote.setCode("111");
		productPromoteService.findProductPromote(findProductPromote);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商品每日一抢信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findProductPromotePage() throws TsfaServiceException{
		FindProductPromotePage findProductPromotePage = new FindProductPromotePage();
		Page<ProductPromoteDto> page = productPromoteService.findProductPromotePage(findProductPromotePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找商品每日一抢信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findProductPromotes() throws TsfaServiceException{
		FindProductPromotePage findProductPromotePage = new FindProductPromotePage();
		List<ProductPromoteDto> page = productPromoteService.findProductPromotes(findProductPromotePage);
		Assert.assertNotNull(page);
		
	}
	
}
