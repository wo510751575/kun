package com.lj.eshop.dao;

import java.util.List;

import com.lj.eshop.domain.ProductGift;
import com.lj.eshop.dto.FindProductGiftPage;
import com.lj.eshop.dto.ProductGiftDto;

public interface IProductGiftDao {
	int deleteByPrimaryKey(String code);

	int insertSelective(ProductGift record);

	ProductGift selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(ProductGift record);

	List<ProductGiftDto> findProductGifts(FindProductGiftPage findProductGiftPage);

	int findProductGiftPageCount(FindProductGiftPage findProductGiftPage);

	List<ProductGiftDto> findProductGiftPage(FindProductGiftPage findProductGiftPage);
}