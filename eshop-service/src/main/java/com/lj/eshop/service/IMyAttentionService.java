package com.lj.eshop.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.MyAttentionDto;
import com.lj.eshop.dto.FindMyAttentionPage;


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
public interface IMyAttentionService {
	
	/**
	 * 
	 *
	 * 方法说明：添加店铺关注信息
	 *
	 * @param myAttentionDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addMyAttention(MyAttentionDto myAttentionDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找店铺关注信息
	 *
	 * @param findMyAttention
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public MyAttentionDto findMyAttention(MyAttentionDto myAttentionDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询店铺关注信息
	 *
	 * @param findMyAttentionPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<MyAttentionDto>  findMyAttentions(FindMyAttentionPage findMyAttentionPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改店铺关注信息
	 *
	 * @param updateMyAttention
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateMyAttention(MyAttentionDto myAttentionDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询店铺关注信息
	 *
	 * @param findMyAttentionPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<MyAttentionDto> findMyAttentionPage(FindMyAttentionPage findMyAttentionPage) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：统计关注人数
	 *
	 * @param findMyAttentionPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年9月5日
	 *
	 */
	int findMyAttentionPageCount(FindMyAttentionPage findMyAttentionPage)throws TsfaServiceException;

}
