package com.lj.eshop.dao;

import java.util.List;

import com.lj.eshop.domain.ProductPromote;
import com.lj.eshop.dto.FindProductPromotePage;
import com.lj.eshop.dto.ProductPromoteDto;

public interface IProductPromoteDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(ProductPromote record);

    ProductPromote selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ProductPromote record);

	List<ProductPromoteDto> findProductPromotes(FindProductPromotePage findProductPromotePage);

	int findProductPromotePageCount(FindProductPromotePage findProductPromotePage);

	List<ProductPromoteDto> findProductPromotePage(FindProductPromotePage findProductPromotePage);
}