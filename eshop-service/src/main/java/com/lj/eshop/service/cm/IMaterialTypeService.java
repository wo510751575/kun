package com.lj.eshop.service.cm;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市深圳扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.cm.AddMaterialType;
import com.lj.eshop.dto.cm.AddMaterialTypeReturn;
import com.lj.eshop.dto.cm.DelMaterialType;
import com.lj.eshop.dto.cm.DelMaterialTypeReturn;
import com.lj.eshop.dto.cm.FindMaterialType;
import com.lj.eshop.dto.cm.FindMaterialTypePage;
import com.lj.eshop.dto.cm.FindMaterialTypePageReturn;
import com.lj.eshop.dto.cm.FindMaterialTypeReturn;
import com.lj.eshop.dto.cm.FindMaterialTypesApp;
import com.lj.eshop.dto.cm.FindMaterialTypesAppReturn;
import com.lj.eshop.dto.cm.UpdateMaterialType;
import com.lj.eshop.dto.cm.UpdateMaterialTypeReturn;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 *         CreateDate: 2017-06-14
 */
public interface IMaterialTypeService {

	/**
	 * 
	 *
	 * 方法说明：添加素材类型表信息
	 *
	 * @param addMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public AddMaterialTypeReturn addMaterialType(AddMaterialType addMaterialType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找素材类型表信息
	 *
	 * @param findMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public FindMaterialTypeReturn findMaterialType(FindMaterialType findMaterialType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除素材类型表信息
	 *
	 * @param delMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public DelMaterialTypeReturn delMaterialType(DelMaterialType delMaterialType) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改素材类型表信息
	 *
	 * @param updateMaterialType
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 */
	public UpdateMaterialTypeReturn updateMaterialType(UpdateMaterialType updateMaterialType)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找素材类型表信息
	 *
	 * @param findMaterialTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public Page<FindMaterialTypePageReturn> findMaterialTypePage(FindMaterialTypePage findMaterialTypePage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找素材类型列表
	 *
	 * @param findMaterialType
	 * @return
	 *
	 * @author 段志鹏 CreateDate: 2017年6月23日
	 *
	 */
	public List<FindMaterialTypePageReturn> findMaterialTypes(FindMaterialTypePage findMaterialTypePage);

	/**
	 * 
	 *
	 * 方法说明：素材类型列表_APP用
	 *
	 * @param findMaterialTypesApp
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年7月15日
	 *
	 */
	public List<FindMaterialTypesAppReturn> findMaterialTypesApp(FindMaterialTypesApp findMaterialTypesApp);

	/**
	 * 
	 *
	 * 方法说明：增加类型总数
	 *
	 * @param code      类型编码
	 * @param decrement 增加数量
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月20日
	 *
	 */
	public int incrementTypeCountByPrimaryKey(String code, Integer increment);

	/**
	 * 
	 *
	 * 方法说明：
	 *
	 * @param code      类型编码
	 * @param decrement 减少数量
	 * @return
	 *
	 * @author 武鹏飞 CreateDate: 2017年7月20日
	 *
	 */
	public int decrementTypeCountByPrimaryKey(String code, Integer decrement);

	/**
	 *
	 *
	 * 方法说明：查找公共和个人素材类型表信息
	 *
	 * @param findMaterialTypePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	Page<FindMaterialTypePageReturn> findMaterialTypeForMemberPage(FindMaterialTypePage findMaterialTypePage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：素材类型列表_电商用
	 *
	 * @param findMaterialTypesApp
	 * @return
	 *
	 * @author 林进权 CreateDate: 2017年9月23日
	 *
	 */
	public List<FindMaterialTypesAppReturn> findMaterialTypesAppEc(FindMaterialTypesApp findMaterialTypesApp);

}
