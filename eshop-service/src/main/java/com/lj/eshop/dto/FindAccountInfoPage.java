package com.lj.eshop.dto;

import com.lj.base.core.pagination.PageParamEntity; 

public class FindAccountInfoPage extends PageParamEntity { 
	/** 参数 */
	private static final long serialVersionUID = 1L;
	private AccountInfoDto param;
	public AccountInfoDto getParam() { 
	return param;} 
	public void setParam(AccountInfoDto param) { 
	this.param = param;} 
	public FindAccountInfoPage() { 
	super(); 
 }	public FindAccountInfoPage(AccountInfoDto param) { 
	super(); 
 	this.param = param; 
 }
}
