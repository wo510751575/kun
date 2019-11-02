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
 *         CreateDate: 2017年9月21日
 */
public enum MemberRankGift {

	/** 会员特权开通1年 */
	MEMBER_RANK_GIVE_1("member_rank_give_1", "1年VIP"),
	/** 会员特权开通2年 */
	MEMBER_RANK_GIVE_2("member_rank_give_2", "2年VIP"),
	/** 会员特权开通3年 */
	MEMBER_RANK_GIVE_3("member_rank_give_3", "3年VIP"),

	;

	MemberRankGift(String value, String name) {
		this.value = value;
		this.name = name;
	}

	private String value;// DB 存贮值
	private String name;// 值对应的中文描述

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
