package com.lj.eshop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2032123592185964192L;

	/** */
	private String code;

	/** 账号 */
	private String accNo;

	/** 会员编码 */
	private String mbrCode;

	/** 可用金额 */
	private BigDecimal cashAmt;

	/** 特权可用金额 */
	private BigDecimal rankCashAmt;

	/** 总提现金额 */
	private BigDecimal totalWithdrawAmt;

	/** 账户类型 0：资金账户 1：其他 */
	private String type;

	/** 状态 0：激活 1：正常 2：冻结 */
	private String status;

	/** 赠送金额 */
	private BigDecimal freeAmt;

	/** 提现密码 */
	private String payPwd;

	/** 更新时间 */
	private Date updateTime;

	/** */
	private String creater;

	/** */
	private Date createTime;
	/** 累计收益 */
	private BigDecimal incomeAmt;

	/** 提现密码连续输入错误次数 */
	private Integer wrongCnt;

	/** 提现密码输入错误的最新时间 */
	private Date wrongTime;
	/**
	 * 历史花销
	 */
	private BigDecimal payedAmount;
	/**
	 * 是否已设置支付密码
	 */
	private Boolean isSetPwd;
	/**
	 * 是否抢单
	 */
	private Boolean isGrab;

	public Boolean getIsGrab() {
		return isGrab;
	}

	public void setIsGrab(Boolean isGrab) {
		this.isGrab = isGrab;
	}

	public Boolean getIsSetPwd() {
		return isSetPwd;
	}

	public void setIsSetPwd(Boolean isSetPwd) {
		this.isSetPwd = isSetPwd;
	}

	/**
	 * @return the payedAmount
	 */
	public BigDecimal getPayedAmount() {
		return payedAmount;
	}

	/**
	 * @param payedAmount the payedAmount to set
	 */
	public void setPayedAmount(BigDecimal payedAmount) {
		this.payedAmount = payedAmount;
	}

	/**
	 * @return the totalWithdrawAmt
	 */
	public BigDecimal getTotalWithdrawAmt() {
		if (totalWithdrawAmt != null) {
			totalWithdrawAmt = totalWithdrawAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return totalWithdrawAmt;
	}

	/**
	 * @param totalWithdrawAmt the totalWithdrawAmt to set
	 */
	public void setTotalWithdrawAmt(BigDecimal totalWithdrawAmt) {
		this.totalWithdrawAmt = totalWithdrawAmt;
	}

	/**
	 * @return the freeAmt
	 */
	public BigDecimal getFreeAmt() {
		if (freeAmt != null) {
			freeAmt = freeAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return freeAmt;
	}

	/**
	 * @param freeAmt the freeAmt to set
	 */
	public void setFreeAmt(BigDecimal freeAmt) {
		this.freeAmt = freeAmt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo == null ? null : accNo.trim();
	}

	public String getMbrCode() {
		return mbrCode;
	}

	public void setMbrCode(String mbrCode) {
		this.mbrCode = mbrCode == null ? null : mbrCode.trim();
	}

	public BigDecimal getCashAmt() {
		if (cashAmt != null) {
			cashAmt = cashAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return cashAmt;
	}

	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd == null ? null : payPwd.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the incomeAmt
	 */
	public BigDecimal getIncomeAmt() {
		if (incomeAmt != null) {
			incomeAmt = incomeAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return incomeAmt;
	}

	/**
	 * @param incomeAmt the incomeAmt to set
	 */
	public void setIncomeAmt(BigDecimal incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

	public BigDecimal getRankCashAmt() {
		if (rankCashAmt != null) {
			rankCashAmt = rankCashAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return rankCashAmt;
	}

	public void setRankCashAmt(BigDecimal rankCashAmt) {
		this.rankCashAmt = rankCashAmt;
	}

	/**
	 * @return the wrongCnt
	 */
	public Integer getWrongCnt() {
		return wrongCnt;
	}

	/**
	 * @param wrongCnt the wrongCnt to set
	 */
	public void setWrongCnt(Integer wrongCnt) {
		this.wrongCnt = wrongCnt;
	}

	/**
	 * @return the wrongTime
	 */
	public Date getWrongTime() {
		return wrongTime;
	}

	/**
	 * @param wrongTime the wrongTime to set
	 */
	public void setWrongTime(Date wrongTime) {
		this.wrongTime = wrongTime;
	}

}
