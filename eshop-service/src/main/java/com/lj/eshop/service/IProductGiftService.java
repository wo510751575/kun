package com.lj.eshop.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.FindProductGiftPage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.ProductGiftDto;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 *         CreateDate: 2017.12.14
 */
public interface IProductGiftService {

	/**
	 * 
	 *
	 * 方法说明：添加赠品礼包信息
	 *
	 * @param productGiftDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addProductGift(ProductGiftDto productGiftDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找赠品礼包信息
	 *
	 * @param findProductGift
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public ProductGiftDto findProductGift(ProductGiftDto productGiftDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询赠品礼包信息
	 *
	 * @param findProductGiftPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<ProductGiftDto> findProductGifts(FindProductGiftPage findProductGiftPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改赠品礼包信息
	 *
	 * @param updateProductGift
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateProductGift(ProductGiftDto productGiftDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询赠品礼包信息
	 *
	 * @param findProductGiftPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<ProductGiftDto> findProductGiftPage(FindProductGiftPage findProductGiftPage)
			throws TsfaServiceException;

}
