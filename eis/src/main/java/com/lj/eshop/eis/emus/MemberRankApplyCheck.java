/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.eis.emus;

/**
 * 
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 
 * @author 林进权
 * 
 *         CreateDate: 2017年10月11日
 */
public enum MemberRankApplyCheck {
	/** 未申请. */
	NOT_APPLY("-1", "未申请"),
	/** 未支付. */
	IN_APPLY("0", "未支付"),
	/** 正常. */
	NORMAL("1", "正常"),
	/** 未填写地址. */
	FAIL("2", "未填写地址或未选择礼包"),
	/** 未绑定手机号. */
	NOT_MOBILE("3", "未绑定手机号或未填写密码"),;

	MemberRankApplyCheck(String value, String chName) {
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
