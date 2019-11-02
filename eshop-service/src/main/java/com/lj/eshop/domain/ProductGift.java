package com.lj.eshop.domain;

import java.util.Date;

public class ProductGift {
    /**
     *  .
     */
    private String code;

    /**
     * 礼包编号 .
     */
    private String giftCode;

    /**
     * 礼品名称 .
     */
    private String giftName;

    /**
     * 礼品名称 .
     */
    private String name;

    /**
     * 0:启用 1停用 .
     */
    private String status;

    /**
     * 创建人 .
     */
    private String creater;

    /**
     *  .
     */
    private Date createTime;

    /**
     *  .
     *
     */
    public String getCode() {
        return code;
    }

    /**
     *  .
     *
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 礼包编号 .
     *
     */
    public String getGiftCode() {
        return giftCode;
    }

    /**
     * 礼包编号 .
     *
     */
    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode == null ? null : giftCode.trim();
    }

    /**
     * 礼品名称 .
     *
     */
    public String getGiftName() {
        return giftName;
    }

    /**
     * 礼品名称 .
     *
     */
    public void setGiftName(String giftName) {
        this.giftName = giftName == null ? null : giftName.trim();
    }

    /**
     * 礼品名称 .
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 礼品名称 .
     *
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 0:启用 1停用 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 0:启用 1停用 .
     *
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人 .
     *
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 创建人 .
     *
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /**
     *  .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *  .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductGift [code=").append(code);
        builder.append(",giftCode=").append(giftCode); 
        builder.append(",giftName=").append(giftName); 
        builder.append(",name=").append(name); 
        builder.append(",status=").append(status); 
        builder.append(",creater=").append(creater); 
        builder.append(",createTime=").append(createTime); 
        builder.append("]");
        return builder.toString();
    }
}