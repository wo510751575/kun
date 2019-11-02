package com.lj.eshop.dao;

import java.util.List;
import com.lj.eshop.domain.ProductTexture;
import com.lj.eshop.dto.FindProTexturePage;
import com.lj.eshop.dto.ProductTextureDto;

public interface IProTextureDao {
	int insertSelective(ProductTexture record);

	ProductTexture selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ProductTexture record);
    
    List<ProductTextureDto> findProTexturePage(FindProTexturePage findProTexturePage);

 	int findProTexturePageCount(FindProTexturePage findProTexturePage);
 	
 	List<ProductTextureDto> findProTextures(FindProTexturePage findProTexturePage);
}
