package com.lj.eshop.service;

import java.util.List;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.FindProTexturePage;
import com.lj.eshop.dto.ProductTextureDto;

public interface IProductTextureService {
	/**
	 * 
	 *
	 * 方法说明：添加商品材质信息
	 *
	 * @param proTextureDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void addProductTexture(ProductTextureDto proTextureDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找商品材质信息
	 *
	 * @param findProTexture
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public ProductTextureDto findProTexture(ProductTextureDto proTextureDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询商品材质信息
	 *
	 * @param findProTextures
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public List<ProductTextureDto>  findProTextures(FindProTexturePage findProTexturePage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改商品材质信息
	 *
	 * @param updateProTexture
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public void updateProTexture(ProductTextureDto sysSpeDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询商品材质信息
	 *
	 * @param findProTexturePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017-08-22
	 *
	 */
	public Page<ProductTextureDto> findProTexturePage(FindProTexturePage findProTexturePage) throws TsfaServiceException;
	
}
