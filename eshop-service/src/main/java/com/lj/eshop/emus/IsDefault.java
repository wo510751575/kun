/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.emus;

/**
 * 
 * 类说明：是否默认。
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 
 * @author lhy
 *   
 * CreateDate: 2017年8月30日
 */
public enum IsDefault {
	/** 是. */
	YES("0", "是"),

	/** 否. */
	NO("1", "否");
	
	IsDefault(String value, String chName) {
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
