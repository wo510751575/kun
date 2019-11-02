package com.lj.eshop.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.WithdrawDto;
import com.lj.eshop.dto.FindWithdrawPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：提现申请Service
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2017-08-22
 */
public interface IWithdrawService {
	
	/**
	 * 
	 *
	 * 方法说明：添加提现申请信息
	 *
	 * @param withdrawDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addWithdraw(WithdrawDto withdrawDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找提现申请信息
	 *
	 * @param findWithdraw
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public WithdrawDto findWithdraw(WithdrawDto withdrawDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询提现申请信息
	 *
	 * @param findWithdrawPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<WithdrawDto>  findWithdraws(FindWithdrawPage findWithdrawPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改提现申请信息
	 *
	 * @param updateWithdraw
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateWithdraw(WithdrawDto withdrawDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询提现申请信息
	 *
	 * @param findWithdrawPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<WithdrawDto> findWithdrawPage(FindWithdrawPage findWithdrawPage) throws TsfaServiceException;
	

}
