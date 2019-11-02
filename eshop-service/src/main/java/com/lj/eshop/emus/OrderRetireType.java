/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.emus;

/**
 * 
 * 
 * 类说明：退换货申请类型
 *  
 * 
 * <p>
 * 详细描述：0:退货 1:换货
 *   
 * @Company: 小坤有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2017年8月30日
 */
public enum OrderRetireType {
	/** 退货 . */
	SUCCESS("0", "退货 "),

	/** 换货. */
	FAIL("1", "换货");
	
	OrderRetireType(String value, String chName) {
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
