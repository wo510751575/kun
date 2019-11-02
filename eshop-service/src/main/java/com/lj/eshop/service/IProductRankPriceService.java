package com.lj.eshop.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.FindProductRankPricePage;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.ProductRankPriceDto;

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
public interface IProductRankPriceService {

	/**
	 * 
	 *
	 * 方法说明：添加商品特权价格信息
	 *
	 * @param productRankPriceDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addOrUpdateProductRankPrice(ProductRankPriceDto productRankPriceDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找商品特权价格信息
	 *
	 * @param findProductRankPrice
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public ProductRankPriceDto findProductRankPrice(ProductRankPriceDto productRankPriceDto)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：不分页查询商品特权价格信息
	 *
	 * @param findProductRankPricePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<ProductRankPriceDto> findProductRankPrices(FindProductRankPricePage findProductRankPricePage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询商品特权价格信息
	 *
	 * @param findProductRankPricePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<ProductRankPriceDto> findProductRankPricePage(FindProductRankPricePage findProductRankPricePage)
			throws TsfaServiceException;

}
