package com.lj.eshop.service.impl.cm;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.eshop.service.cm.IActivityJobService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-07-24
 */
public class ActivityJobServiceImplTest extends SpringTestCase{

	@Resource
	IActivityJobService activityJobService;


	@Test
	public void activityComTask() throws TsfaServiceException{
		activityJobService.activityComTask();
	}
}
