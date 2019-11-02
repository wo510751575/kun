package com.lj.eshop.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductTextureDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** */
	private String code;

	/** 材质名 */
	private String textureName;

	/** 创建人 */
	private String creater;

	/** 0:启用 1停用 */
	private String status;

	/** */
	private Date createTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ProductTextureDto [code=" + code + ", textureName=" + textureName + ", creater=" + creater + ", status="
				+ status + ", createTime=" + createTime + "]";
	}
	
	
}
