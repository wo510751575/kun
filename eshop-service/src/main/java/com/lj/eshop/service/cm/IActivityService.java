package com.lj.eshop.service.cm;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.cm.activity.AddActivity;
import com.lj.eshop.dto.cm.activity.AddActivityReturn;
import com.lj.eshop.dto.cm.activity.DelActivity;
import com.lj.eshop.dto.cm.activity.DelActivityReturn;
import com.lj.eshop.dto.cm.activity.FindActivity;
import com.lj.eshop.dto.cm.activity.FindActivityPage;
import com.lj.eshop.dto.cm.activity.FindActivityPageReturn;
import com.lj.eshop.dto.cm.activity.FindActivityReturn;
import com.lj.eshop.dto.cm.activity.UpdateActivity;
import com.lj.eshop.dto.cm.activity.UpdateActivityForReadDto;
import com.lj.eshop.dto.cm.activity.UpdateActivityReturn;

/**
 * 
 * 
 * 类说明：活动表接口
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 
 * @author 邹磊
 * 
 *         CreateDate: 2017年7月24日
 */
public interface IActivityService {

	/**
	 * 
	 *
	 * 方法说明：添加活动表信息
	 *
	 * @param addActivity
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public AddActivityReturn addActivity(AddActivity addActivity) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找活动表信息
	 *
	 * @param findActivity
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public FindActivityReturn findActivity(FindActivity findActivity) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除活动表信息
	 *
	 * @param delActivity
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public DelActivityReturn delActivity(DelActivity delActivity) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改活动表信息
	 *
	 * @param updateActivity
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public UpdateActivityReturn updateActivity(UpdateActivity updateActivity) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找活动表信息
	 *
	 * @param findActivityPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年9月9日
	 *
	 */
	public Page<FindActivityPageReturn> findActivityPage(FindActivityPage findActivityPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找活动列表
	 *
	 * @param findActivityDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月24日
	 *
	 */
	public List<FindActivityPageReturn> findActivitys(FindActivityPage findActivityPage) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：点击量
	 *
	 * @param UpdateActivityForReadDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月25日
	 * @deprecated
	 */
	public void updateActivityForRead(UpdateActivityForReadDto UpdateActivityForReadDto) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：增加活动分享量
	 *
	 * @param updateActivity
	 * @throws TsfaServiceException
	 *
	 * @author  CreateDate: 2017年8月15日
	 *
	 */
	public void addActivityForShare(UpdateActivity updateActivity) throws TsfaServiceException;
}
