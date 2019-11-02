package com.lj.eshop.service.cm;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.cm.AddGreetClient;
import com.lj.eshop.dto.cm.AddGreetClientReturn;
import com.lj.eshop.dto.cm.DelGreetClient;
import com.lj.eshop.dto.cm.DelGreetClientReturn;
import com.lj.eshop.dto.cm.FindGreetClient;
import com.lj.eshop.dto.cm.FindGreetClientForDayDto;
import com.lj.eshop.dto.cm.FindGreetClientPage;
import com.lj.eshop.dto.cm.FindGreetClientPageReturn;
import com.lj.eshop.dto.cm.FindGreetClientReturn;
import com.lj.eshop.dto.cm.FindGreetClientReturnDto;
import com.lj.eshop.dto.cm.UpdateGreetClient;
import com.lj.eshop.dto.cm.UpdateGreetClientReturn;

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
public interface IGreetClientService {

	/**
	 * 
	 *
	 * 方法说明：添加问候客户表信息
	 *
	 * @param addGreetClient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public AddGreetClientReturn addGreetClient(AddGreetClient addGreetClient) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找问候客户表信息
	 *
	 * @param findGreetClient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public FindGreetClientReturn findGreetClient(FindGreetClient findGreetClient) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：删除问候客户表信息
	 *
	 * @param delGreetClient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public DelGreetClientReturn delGreetClient(DelGreetClient delGreetClient) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改问候客户表信息
	 *
	 * @param updateGreetClient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public UpdateGreetClientReturn updateGreetClient(UpdateGreetClient updateGreetClient) throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找问候客户表信息
	 *
	 * @param findGreetClientPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public Page<FindGreetClientPageReturn> findGreetClientPage(FindGreetClientPage findGreetClientPage)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：查找当日问候信息(今日工作)
	 *
	 * @param findGreetClientDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 邹磊 CreateDate: 2017年7月20日
	 *
	 */
	public FindGreetClientReturnDto findGreetClientForDay(FindGreetClientForDayDto findGreetClientForDayDto)
			throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：添加问候客户表信息
	 *
	 * @param addGreetClient
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年6月9日
	 *
	 */
	public AddGreetClientReturn addGreetClientEc(AddGreetClient addGreetClient) throws TsfaServiceException;
}
