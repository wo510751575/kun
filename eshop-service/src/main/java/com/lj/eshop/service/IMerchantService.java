package com.lj.eshop.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.MerchantDto;
import com.lj.eshop.dto.FindMerchantPage;


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
public interface IMerchantService {
	
	/**
	 * 
	 *
	 * 方法说明：添加商户信息
	 *
	 * @param merchantDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addMerchant(MerchantDto merchantDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找商户信息
	 *
	 * @param findMerchant
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public MerchantDto findMerchant(MerchantDto merchantDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询商户信息
	 *
	 * @param findMerchantPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<MerchantDto>  findMerchants(FindMerchantPage findMerchantPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改商户信息
	 *
	 * @param updateMerchant
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateMerchant(MerchantDto merchantDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询商户信息
	 *
	 * @param findMerchantPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<MerchantDto> findMerchantPage(FindMerchantPage findMerchantPage) throws TsfaServiceException;
	

}
