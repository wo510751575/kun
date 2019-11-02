package com.lj.eshop.dto;

import com.lj.base.core.pagination.PageParamEntity;

public class FindProductGiftPage extends PageParamEntity {
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private ProductGiftDto param;

	public ProductGiftDto getParam() {
		return param;
	}

	public void setParam(ProductGiftDto param) {
		this.param = param;
	}

	public FindProductGiftPage() {
		super();
	}

	public FindProductGiftPage(ProductGiftDto param) {
		super();
		this.param = param;
	}
}
