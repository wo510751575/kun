package com.lj.eshop.dao.cm;

import java.util.List;

import com.lj.eshop.domain.cm.MerchantParams;
import com.lj.eshop.dto.cm.merchantParams.FindMerchantParamsPage;
import com.lj.eshop.dto.cm.merchantParams.FindMerchantParamsPageReturn;

public interface IMerchantParamsDao {
    int deleteByPrimaryKey(String code);

    int insert(MerchantParams record);

    int insertSelective(MerchantParams record);

    MerchantParams selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MerchantParams record);

    int updateByPrimaryKey(MerchantParams record);
    
    List<FindMerchantParamsPageReturn> findMerchantParamsPage(FindMerchantParamsPage findMerchantParamsPage);

   	int findMerchantParamsPageCount(FindMerchantParamsPage findMerchantParamsPage);
    
    
}