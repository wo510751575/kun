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
 * 类说明：订单状态
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company:
 * @author
 * 
 * 		CreateDate: 2017年8月28日
 */
public enum OrderStatus {
	DQR("0", "待确认"), COMPLETED("2", "已完成"), RETURNS("3", "申诉中"), CHANGE("4", "被申诉"), CANCEL("5", "已取消"),;

	OrderStatus(String value, String chName) {
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
