package com.lj.eshop.service.cm;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.cm.merchantParams.AddMerchantParams;
import com.lj.eshop.dto.cm.merchantParams.DelMerchantParams;
import com.lj.eshop.dto.cm.merchantParams.FindMerchantParams;
import com.lj.eshop.dto.cm.merchantParams.FindMerchantParamsPage;
import com.lj.eshop.dto.cm.merchantParams.FindMerchantParamsPageReturn;
import com.lj.eshop.dto.cm.merchantParams.FindMerchantParamsReturn;
import com.lj.eshop.dto.cm.merchantParams.UpdateMerchantParams;

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
public interface IMerchantParamsService {

	/**
	 * 
	 *
	 * 方法说明：添加商户参数配置信息
	 *
	 * @param addMerchantParams
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public void addMerchantParams(AddMerchantParams addMerchantParams) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找商户参数配置信息
	 *
	 * @param findMerchantParams
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public FindMerchantParamsReturn findMerchantParams(FindMerchantParams findMerchantParams)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除商户参数配置信息
	 *
	 * @param delMerchantParams
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public void delMerchantParams(DelMerchantParams delMerchantParams) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改商户参数配置信息
	 *
	 * @param updateMerchantParams
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public void updateMerchantParams(UpdateMerchantParams updateMerchantParams) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询商户参数配置信息
	 *
	 * @param findMerchantParamsPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public Page<FindMerchantParamsPageReturn> findMerchantParamsPage(FindMerchantParamsPage findMerchantParamsPage)
			throws TsfaServiceException;

}
