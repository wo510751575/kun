package com.lj.eshop.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市深圳扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.ProductFlagDto;
import com.lj.eshop.dto.FindProductFlagPage;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2017-08-22
 */
public interface IProductFlagService {
	
	/**
	 * 
	 *
	 * 方法说明：添加商品标记关联信息
	 *
	 * @param productFlagDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addProductFlag(ProductFlagDto productFlagDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找商品标记关联信息
	 *
	 * @param findProductFlag
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public ProductFlagDto findProductFlag(ProductFlagDto productFlagDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询商品标记关联信息
	 *
	 * @param findProductFlagPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<ProductFlagDto>  findProductFlags(FindProductFlagPage findProductFlagPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改商品标记关联信息
	 *
	 * @param updateProductFlag
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateProductFlag(ProductFlagDto productFlagDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询商品标记关联信息
	 *
	 * @param findProductFlagPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<ProductFlagDto> findProductFlagPage(FindProductFlagPage findProductFlagPage) throws TsfaServiceException;
	
	/**
	 * 方法说明：根据商品CODE删除商品标签.
	 * @param productFlagDto
	 * @return
	 *
	 * @author lhy  2017年8月29日
	 *
	 */
	public int  deleteByProductCode(ProductFlagDto productFlagDto);

}
