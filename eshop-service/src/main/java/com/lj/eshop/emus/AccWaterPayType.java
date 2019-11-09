/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.emus;

/**
 * 
 * 类说明：账户流水支付方式。
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company:
 * @author lhy
 * 
 *         CreateDate: 2017年8月23日
 */
public enum AccWaterPayType {
	// 1 支付宝收钱码 / 2 支付宝PID / 3微信收钱码 / 4云闪付 / 5拼多多
	ALIQRCODE("1", "支付宝收钱码"), ALIPID("2", "支付宝PID"), WX("3", "微信收钱码"), YSF("4", "云闪付"), PDD("5", "拼多多"),;
	AccWaterPayType(String value, String chName) {
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

	/**
	 * 方法说明：通过key获取值
	 * 
	 * @author 林进权 CreateDate: 2017年8月29日
	 */
	public static String getChName(String val) {
		AccWaterPayType[] enums = AccWaterPayType.values();
		for (int i = 0; i < enums.length; i++) {
			if (enums[i].getValue().equals(val)) {
				return enums[i].getChName();
			}
		}
		return "";
	}
}
