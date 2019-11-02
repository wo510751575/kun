package com.lj.eshop.emus;

import java.math.BigDecimal;

/**
 * 
 * 
 * 类说明：开通会员价格
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 深圳扬恩科技有限公司
 * @author 段志鹏
 * 
 *         CreateDate: 2017年9月21日
 */
public enum MemberRankAmt {

	/** 会员特权开通1年 */
	MEMBER_RANK_GIVE_1("member_rank_give_1", new BigDecimal(699)),
	/** 会员特权开通2年 */
	MEMBER_RANK_GIVE_2("member_rank_give_2", new BigDecimal(1398)),
	/** 会员特权开通3年 */
	MEMBER_RANK_GIVE_3("member_rank_give_3", new BigDecimal(2097)),

	;

	MemberRankAmt(String value, BigDecimal amount) {
		this.value = value;
		this.amount = amount;
	}

	private String value;// DB 存贮值
	private BigDecimal amount;// 值对应的中文描述

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
