package com.lj.eshop.dto.cm.textInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class FindTextInfoReturn.
 */
public class FindTextInfoReturn implements Serializable { 

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4074196573401458012L;

	/**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 商户名称 .
     */
    private String merchantName;

    /**
     * 话术类型 .
     */
    private String textType;

    /**
     * 话术内容 .
     */
    private String content;

    /**
     * 话术维度开始 .
     */
    private String dimStart;

    /**
     * 话术维度结束 .
     */
    private String dimEnd;

    /**
     * 话术维度关键字 .
     */
    private String dimKeyWord;

    /**
     * 创建时间 .
     */
    private Date createDate;

	/**
	 * Gets the cODE .
	 *
	 * @return the cODE 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the cODE .
	 *
	 * @param code the new cODE 
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the 商户编号 .
	 *
	 * @return the 商户编号 
	 */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the new 商户编号 
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 商户名称 .
	 *
	 * @return the 商户名称 
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * Sets the 商户名称 .
	 *
	 * @param merchantName the new 商户名称 
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * Gets the 话术类型 .
	 *
	 * @return the 话术类型 
	 */
	public String getTextType() {
		return textType;
	}

	/**
	 * Sets the 话术类型 .
	 *
	 * @param textType the new 话术类型 
	 */
	public void setTextType(String textType) {
		this.textType = textType;
	}

	/**
	 * Gets the 话术内容 .
	 *
	 * @return the 话术内容 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the 话术内容 .
	 *
	 * @param content the new 话术内容 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the 话术维度开始 .
	 *
	 * @return the 话术维度开始 
	 */
	public String getDimStart() {
		return dimStart;
	}

	/**
	 * Sets the 话术维度开始 .
	 *
	 * @param dimStart the new 话术维度开始 
	 */
	public void setDimStart(String dimStart) {
		this.dimStart = dimStart;
	}

	/**
	 * Gets the 话术维度结束 .
	 *
	 * @return the 话术维度结束 
	 */
	public String getDimEnd() {
		return dimEnd;
	}

	/**
	 * Sets the 话术维度结束 .
	 *
	 * @param dimEnd the new 话术维度结束 
	 */
	public void setDimEnd(String dimEnd) {
		this.dimEnd = dimEnd;
	}

	/**
	 * Gets the 话术维度关键字 .
	 *
	 * @return the 话术维度关键字 
	 */
	public String getDimKeyWord() {
		return dimKeyWord;
	}

	/**
	 * Sets the 话术维度关键字 .
	 *
	 * @param dimKeyWord the new 话术维度关键字 
	 */
	public void setDimKeyWord(String dimKeyWord) {
		this.dimKeyWord = dimKeyWord;
	}

	/**
	 * Gets the 创建时间 .
	 *
	 * @return the 创建时间 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the 创建时间 .
	 *
	 * @param createDate the new 创建时间 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindTextInfoReturn [code=").append(code)
				.append(", merchantNo=").append(merchantNo)
				.append(", merchantName=").append(merchantName)
				.append(", textType=").append(textType).append(", content=")
				.append(content).append(", dimStart=").append(dimStart)
				.append(", dimEnd=").append(dimEnd).append(", dimKeyWord=")
				.append(dimKeyWord).append("]");
		return builder.toString();
	}
}
