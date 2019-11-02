
import java.util.List;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.eshop.dto.AccountInfoDto;
import com.lj.eshop.dto.FindAccountInfoPage;
import com.lj.eshop.service.IAccountInfoService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 *         CreateDate: 2017.12.11
 */
public class AccountInfoServiceImplTest extends SpringTestCase {

	@Resource
	IAccountInfoService accountInfoService;

	/**
	 * 
	 *
	 * 方法说明：添加收款账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addAccountInfo() throws TsfaServiceException {
		AccountInfoDto accountInfoDto = new AccountInfoDto();
		// add数据录入
		accountInfoDto.setCode("Code");
		accountInfoDto.setName("Name");
		accountInfoDto.setAccount("Account");
		accountInfoDto.setPid("Pid");
		accountInfoDto.setMbrCode("MbrCode");
		accountInfoDto.setType("1");
		accountInfoService.addAccountInfo(accountInfoDto);

	}

	/**
	 * 
	 *
	 * 方法说明：修改收款账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updateAccountInfo() throws TsfaServiceException {
		AccountInfoDto accountInfoDto = new AccountInfoDto();
		// update数据录入
		accountInfoDto.setCode("Code");
		accountInfoDto.setName("Name");
		accountInfoDto.setAccount("Account");
		accountInfoDto.setPid("Pid");
		accountInfoDto.setMbrCode("MbrCode");
		accountInfoDto.setType("1");
		accountInfoService.updateAccountInfo(accountInfoDto);

	}

	/**
	 * 
	 *
	 * 方法说明：查找收款账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAccountInfo() throws TsfaServiceException {
		AccountInfoDto findAccountInfo = new AccountInfoDto();
		findAccountInfo.setCode("111");
		accountInfoService.findAccountInfo(findAccountInfo);
	}

	/**
	 * 
	 *
	 * 方法说明：查找收款账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAccountInfoPage() throws TsfaServiceException {
		FindAccountInfoPage findAccountInfoPage = new FindAccountInfoPage();
		Page<AccountInfoDto> page = accountInfoService.findAccountInfoPage(findAccountInfoPage);
		Assert.assertNotNull(page);

	}

	/**
	 * 
	 *
	 * 方法说明：查找收款账户信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findAccountInfos() throws TsfaServiceException {
		FindAccountInfoPage findAccountInfoPage = new FindAccountInfoPage();
		List<AccountInfoDto> page = accountInfoService.findAccountInfos(findAccountInfoPage);
		Assert.assertNotNull(page);

	}

}
