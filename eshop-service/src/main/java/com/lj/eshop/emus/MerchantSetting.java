package com.lj.eshop.emus;

/**
 * 
 * 
 * 类说明：商户配置
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company:
 * @author
 * 
 * 		CreateDate: 2017年9月21日
 */
public enum MerchantSetting {

	/** 微信appId */
	WEIXIN_APPID("weixin.appid", "微信appId"),
	/** 微信商户号 */
	WEIXIN_MCHID("weixin.mchId", "微信商户号"),
	/** 微信appSecret */
	WEIXIN_APPSECRET("weixin.appSecret", "微信appSecret"),
	/** 微信apiKey */
	WEIXIN_APIKEY("weixin.apiKey", "微信apiKey"),

	/** 银行信息 */
	OFFLINE_BANKCARD("offline.bankCard", "银行信息"),
	/** 商户银行账号 */
	OFFLINE_ACCOUNT("offline.account", "商户银行账号"),
	/** 商户银行限额 */
	OFFLINE_AMT("offline.amt", "商户银行限额"),
	/** 商户公司名称 */
	OFFLINE_COMPANY("offline.name", "商户公司名称"),

	;

	MerchantSetting(String value, String chName) {
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
