package com.lj.eshop.dao;

import java.util.List;

import com.lj.eshop.domain.AccountInfo;
import com.lj.eshop.dto.AccountInfoDto;
import com.lj.eshop.dto.FindAccountInfoPage;

public interface IAccountInfoDao {
	int deleteByPrimaryKey(String code);

	int insertSelective(AccountInfo record);

	AccountInfo selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(AccountInfo record);

	List<AccountInfoDto> findAccountInfos(FindAccountInfoPage findAccountInfoPage);

	int findAccountInfoPageCount(FindAccountInfoPage findAccountInfoPage);

	List<AccountInfoDto> findAccountInfoPage(FindAccountInfoPage findAccountInfoPage);
}