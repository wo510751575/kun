/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.emus;

/**
 * 
 * 类说明：广告类型。
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 
 * @author lhy
 * CreateDate: 2017年8月23日
 */
public enum LoadingBiz {
	/** 首页广告栏. */
	MALE("0", "首页广告栏"),

	/** 商品集散广告栏. */
	FEMALE("1", "商品集散广告栏"),
	
	/** 商品分类广告栏*/
	FMALE("2","商品分类广告栏");
	
	LoadingBiz(String value, String chName) {
		this.value = value;
		this.chName = chName;
	}

	private String value;// DB 存贮值
	private String chName;// 值对应的中文描述
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the chName
	 */
	public String getChName() {
		return chName;
	}
	/**
	 * @param chName the chName to set
	 */
	public void setChName(String chName) {
		this.chName = chName;
	}
	
	
}
