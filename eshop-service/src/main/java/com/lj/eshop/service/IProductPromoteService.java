package com.lj.eshop.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.ProductPromoteDto;
import com.lj.eshop.dto.FindProductPromotePage;

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
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
public interface IProductPromoteService {
	
	/**
	 * 
	 *
	 * 方法说明：添加商品每日一抢信息
	 *
	 * @param productPromoteDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addProductPromote(ProductPromoteDto productPromoteDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找商品每日一抢信息
	 *
	 * @param findProductPromote
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public ProductPromoteDto findProductPromote(ProductPromoteDto productPromoteDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询商品每日一抢信息
	 *
	 * @param findProductPromotePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<ProductPromoteDto>  findProductPromotes(FindProductPromotePage findProductPromotePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改商品每日一抢信息
	 *
	 * @param updateProductPromote
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateProductPromote(ProductPromoteDto productPromoteDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询商品每日一抢信息
	 *
	 * @param findProductPromotePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<ProductPromoteDto> findProductPromotePage(FindProductPromotePage findProductPromotePage) throws TsfaServiceException;
	

}
