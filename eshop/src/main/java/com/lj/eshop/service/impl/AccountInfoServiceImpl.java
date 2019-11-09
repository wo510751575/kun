package com.lj.eshop.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.constant.ErrorCode;
import com.lj.eshop.dao.IAccountInfoDao;
import com.lj.eshop.domain.AccountInfo;
import com.lj.eshop.dto.AccountInfoDto;
import com.lj.eshop.dto.FindAccountInfoPage;
import com.lj.eshop.service.IAccountInfoService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 *         CreateDate: 2017.12.14
 */
@Service
public class AccountInfoServiceImpl implements IAccountInfoService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

	@Resource
	private IAccountInfoDao accountInfoDao;

	@Override
	public void addAccountInfo(AccountInfoDto accountInfoDto) throws TsfaServiceException {
		logger.debug("addAccountInfo(AddAccountInfo addAccountInfo={}) - start", accountInfoDto);

		AssertUtils.notNull(accountInfoDto);
		try {
			AccountInfo accountInfo = new AccountInfo();
			// add数据录入
			accountInfo.setCode(GUID.generateCode());
			accountInfo.setName(accountInfoDto.getName());
			accountInfo.setAccount(accountInfoDto.getAccount());
			accountInfo.setPid(accountInfoDto.getPid());
			accountInfo.setMbrCode(accountInfoDto.getMbrCode());
			accountInfo.setType(accountInfoDto.getType());
			accountInfoDao.insertSelective(accountInfo);
			logger.debug("addAccountInfo(AccountInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增收款账户信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ACCOUNT_INFO_ADD_ERROR, "新增收款账户信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询收款账户信息
	 *
	 * @param findAccountInfoPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	public List<AccountInfoDto> findAccountInfos(FindAccountInfoPage findAccountInfoPage) throws TsfaServiceException {
		AssertUtils.notNull(findAccountInfoPage);
		List<AccountInfoDto> returnList = null;
		try {
			returnList = accountInfoDao.findAccountInfos(findAccountInfoPage);
		} catch (Exception e) {
			logger.error("查找收款账户信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ACCOUNT_INFO_NOT_EXIST_ERROR, "收款账户信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateAccountInfo(AccountInfoDto accountInfoDto) throws TsfaServiceException {
		logger.debug("updateAccountInfo(AccountInfoDto accountInfoDto={}) - start", accountInfoDto);

		AssertUtils.notNull(accountInfoDto);
		AssertUtils.notNullAndEmpty(accountInfoDto.getCode(), "Code不能为空");
		try {
			AccountInfo accountInfo = new AccountInfo();
			// update数据录入
			accountInfo.setCode(accountInfoDto.getCode());
			accountInfo.setName(accountInfoDto.getName());
			accountInfo.setAccount(accountInfoDto.getAccount());
			accountInfo.setPid(accountInfoDto.getPid());
			accountInfo.setMbrCode(accountInfoDto.getMbrCode());
			accountInfo.setType(accountInfoDto.getType());
			AssertUtils.notUpdateMoreThanOne(accountInfoDao.updateByPrimaryKeySelective(accountInfo));
			logger.debug("updateAccountInfo(AccountInfoDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("收款账户信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ACCOUNT_INFO_UPDATE_ERROR, "收款账户信息更新信息错误！", e);
		}
	}

	@Override
	public AccountInfoDto findAccountInfo(AccountInfoDto accountInfoDto) throws TsfaServiceException {
		logger.debug("findAccountInfo(FindAccountInfo findAccountInfo={}) - start", accountInfoDto);

		AssertUtils.notNull(accountInfoDto);
		AssertUtils.notAllNull(accountInfoDto.getCode(), "Code不能为空");
		try {
			AccountInfo accountInfo = accountInfoDao.selectByPrimaryKey(accountInfoDto.getCode());
			if (accountInfo == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.ACCOUNT_INFO_NOT_EXIST_ERROR,"收款账户信息不存在");
			}
			AccountInfoDto findAccountInfoReturn = new AccountInfoDto();
			// find数据录入
			findAccountInfoReturn.setCode(accountInfo.getCode());
			findAccountInfoReturn.setName(accountInfo.getName());
			findAccountInfoReturn.setAccount(accountInfo.getAccount());
			findAccountInfoReturn.setPid(accountInfo.getPid());
			findAccountInfoReturn.setMbrCode(accountInfo.getMbrCode());
			findAccountInfoReturn.setType(accountInfo.getType());
			logger.debug("findAccountInfo(AccountInfoDto) - end - return value={}", findAccountInfoReturn);
			return findAccountInfoReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找收款账户信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ACCOUNT_INFO_FIND_ERROR, "查找收款账户信息信息错误！", e);
		}

	}

	@Override
	public Page<AccountInfoDto> findAccountInfoPage(FindAccountInfoPage findAccountInfoPage)
			throws TsfaServiceException {
		logger.debug("findAccountInfoPage(FindAccountInfoPage findAccountInfoPage={}) - start", findAccountInfoPage);

		AssertUtils.notNull(findAccountInfoPage);
		List<AccountInfoDto> returnList = null;
		int count = 0;
		try {
			count = accountInfoDao.findAccountInfoPageCount(findAccountInfoPage);
			if (count > 0) {
				returnList = accountInfoDao.findAccountInfoPage(findAccountInfoPage);
			}
		} catch (Exception e) {
			logger.error("收款账户信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.ACCOUNT_INFO_FIND_PAGE_ERROR, "收款账户信息不存在错误.！", e);
		}
		Page<AccountInfoDto> returnPage = new Page<AccountInfoDto>(returnList, count, findAccountInfoPage);

		logger.debug("findAccountInfoPage(FindAccountInfoPage) - end - return value={}", returnPage);
		return returnPage;
	}

}
