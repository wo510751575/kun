package com.lj.eshop.dto;

import java.io.Serializable;

public class AccountInfoDto implements Serializable {

	/**
	 * .
	 */
	private String code;

	/**
	 * 姓名 .
	 */
	private String name;

	/**
	 * 账户 .
	 */
	private String account;

	/**
	 * PID/或二维码地址 .
	 */
	private String pid;

	/**
	 * 会员编号 .
	 */
	private String mbrCode;
	/**
	 * 1 支付宝收钱码 / 2 支付宝PID / 3微信收钱码 / 4云闪付 / 5拼多多
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * .
	 *
	 */
	public String getCode() {
		return code;
	}

	/**
	 * .
	 *
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * 姓名 .
	 *
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名 .
	 *
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 账户 .
	 *
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 账户 .
	 *
	 */
	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	/**
	 * PID/或二维码地址 .
	 *
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * PID/或二维码地址 .
	 *
	 */
	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	/**
	 * 会员编号 .
	 *
	 */
	public String getMbrCode() {
		return mbrCode;
	}

	/**
	 * 会员编号 .
	 *
	 */
	public void setMbrCode(String mbrCode) {
		this.mbrCode = mbrCode == null ? null : mbrCode.trim();
	}

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountInfo [code=").append(code);
		builder.append(",name=").append(name);
		builder.append(",account=").append(account);
		builder.append(",pid=").append(pid);
		builder.append(",mbrCode=").append(mbrCode);
		builder.append("]");
		return builder.toString();
	}
}
