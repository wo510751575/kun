package com.lj.eshop.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindProTexturePage extends PageParamEntity {
	
	private static final long serialVersionUID = 1L;
	// 查询条件
	private ProductTextureDto param;
	/**
	 * @return the param
	 */
	public ProductTextureDto getParam() {
		return param;
	}
	/**
	 * @param param the param to set
	 */
	public void setParam(ProductTextureDto param) {
		this.param = param;
	}
}
