package com.lj.eshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lj.eshop.domain.ProductRankPrice;
import com.lj.eshop.dto.FindProductRankPricePage;
import com.lj.eshop.dto.ProductRankPriceDto;

public interface IProductRankPriceDao {
	int deleteByPrimaryKey(String code);

	int insertOrUpdate(ProductRankPrice record);

	ProductRankPrice selectByPrimaryKey(@Param("productCode") String productCode, @Param("rankCode") String rankCode,
			@Param("skuCode") String skuCode);

	List<ProductRankPriceDto> findProductRankPrices(FindProductRankPricePage findProductRankPricePage);

	int findProductRankPricePageCount(FindProductRankPricePage findProductRankPricePage);

	List<ProductRankPriceDto> findProductRankPricePage(FindProductRankPricePage findProductRankPricePage);
}