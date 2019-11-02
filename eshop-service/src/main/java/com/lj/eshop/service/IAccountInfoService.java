package com.lj.eshop.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.AccountInfoDto;
import com.lj.eshop.dto.FindAccountInfoPage;

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
public interface IAccountInfoService {
	
	/**
	 * 
	 *
	 * 方法说明：添加收款账户信息
	 *
	 * @param accountInfoDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addAccountInfo(AccountInfoDto accountInfoDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找收款账户信息
	 *
	 * @param findAccountInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public AccountInfoDto findAccountInfo(AccountInfoDto accountInfoDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询收款账户信息
	 *
	 * @param findAccountInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<AccountInfoDto>  findAccountInfos(FindAccountInfoPage findAccountInfoPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改收款账户信息
	 *
	 * @param updateAccountInfo
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updateAccountInfo(AccountInfoDto accountInfoDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询收款账户信息
	 *
	 * @param findAccountInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<AccountInfoDto> findAccountInfoPage(FindAccountInfoPage findAccountInfoPage) throws TsfaServiceException;
	

}
