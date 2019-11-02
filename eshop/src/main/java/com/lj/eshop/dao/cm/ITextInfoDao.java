package com.lj.eshop.dao.cm;

import java.util.List;

import com.lj.eshop.domain.cm.TextInfo;
import com.lj.eshop.dto.cm.textInfo.FindTextInfo;
import com.lj.eshop.dto.cm.textInfo.FindTextInfoPage;
import com.lj.eshop.dto.cm.textInfo.FindTextInfoPageReturn;

public interface ITextInfoDao {
    int deleteByPrimaryKey(String code);

    int insert(TextInfo record);

    int insertSelective(TextInfo record);

    TextInfo selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(TextInfo record);

    int updateByPrimaryKey(TextInfo record);
    
    List<FindTextInfoPageReturn> findTextInfoPage(FindTextInfoPage findTextInfoPage);

	int findTextInfoPageCount(FindTextInfoPage findTextInfoPage);
	
	 FindTextInfoPageReturn findTextInfoReturnContent(FindTextInfo findTextInfo);	 
}