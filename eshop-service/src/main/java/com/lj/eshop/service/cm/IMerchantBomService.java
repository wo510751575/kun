package com.lj.eshop.service.cm;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.cm.AddMerchantBom;
import com.lj.eshop.dto.cm.AddMerchantBomReturn;
import com.lj.eshop.dto.cm.DelMerchantBom;
import com.lj.eshop.dto.cm.DelMerchantBomReturn;
import com.lj.eshop.dto.cm.FindMerchantBom;
import com.lj.eshop.dto.cm.FindMerchantBomPage;
import com.lj.eshop.dto.cm.FindMerchantBomPageReturn;
import com.lj.eshop.dto.cm.FindMerchantBomReturn;
import com.lj.eshop.dto.cm.UpdateMerchantBom;
import com.lj.eshop.dto.cm.UpdateMerchantBomReturn;

/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗书明
 * 
 * 
 *         CreateDate: 2017-06-21
 */
public interface IMerchantBomService {

	/**
	 * 
	 *
	 * 方法说明：添加商户产品表信息
	 *
	 * @param addMerchantBom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public AddMerchantBomReturn addMerchantBom(AddMerchantBom addMerchantBom) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找商户产品表信息
	 *
	 * @param findMerchantBom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public FindMerchantBomReturn findMerchantBom(FindMerchantBom findMerchantBom) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除商户产品表信息
	 *
	 * @param delMerchantBom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public DelMerchantBomReturn delMerchantBom(DelMerchantBom delMerchantBom) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改商户产品表信息
	 *
	 * @param updateMerchantBom
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public UpdateMerchantBomReturn updateMerchantBom(UpdateMerchantBom updateMerchantBom) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找商户产品表信息
	 *
	 * @param findMerchantBomPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
	public Page<FindMerchantBomPageReturn> findMerchantBomPage(FindMerchantBomPage findMerchantBomPage)
			throws TsfaServiceException;

}
