package com.lj.eshop.domain;

import java.util.Date;

public class ProductTexture {
	 /** */
    private String code;

    /**  材质名*/
    private String textureName;

    /** 创建人*/
    private String creater;

    /** 0:启用 1停用*/
    private String status;

    /** */
    private Date createTime;

    public void setTextureName(String textureName) {
    	 this.textureName = textureName == null ? null : textureName.trim();
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getTextureName() {
        return textureName;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
