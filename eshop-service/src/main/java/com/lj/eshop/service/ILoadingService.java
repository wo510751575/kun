package com.lj.eshop.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市深圳扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.lj.eshop.dto.LoadingDto;
import com.lj.eshop.dto.FindLoadingPage;


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
public interface ILoadingService {
	
	/**
	 * 
	 *
	 * 方法说明：添加广告信息
	 *
	 * @param loadingDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addLoading(LoadingDto loadingDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找广告信息
	 *
	 * @param findLoading
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public LoadingDto findLoading(LoadingDto loadingDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询广告信息
	 *
	 * @param findLoadingPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<LoadingDto>  findLoadings(FindLoadingPage findLoadingPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改广告信息
	 *
	 * @param updateLoading
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateLoading(LoadingDto loadingDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询广告信息
	 *
	 * @param findLoadingPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<LoadingDto> findLoadingPage(FindLoadingPage findLoadingPage) throws TsfaServiceException;
	

}
