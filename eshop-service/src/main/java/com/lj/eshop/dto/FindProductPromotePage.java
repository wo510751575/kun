package com.lj.eshop.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindProductPromotePage extends PageParamEntity { 
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private ProductPromoteDto param;
	public ProductPromoteDto getParam() { 
	return param;} 
	public void setParam(ProductPromoteDto param) { 
	this.param = param;} 
	public FindProductPromotePage() { 
	super(); 
 }	public FindProductPromotePage(ProductPromoteDto param) { 
	super(); 
 	this.param = param; 
 }
}
