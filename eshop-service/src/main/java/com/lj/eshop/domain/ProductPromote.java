package com.lj.eshop.domain;

import java.util.Date;
import java.util.List;

import com.lj.eshop.dto.ProductDto;
import com.lj.eshop.dto.ProductSkuDto;

public class ProductPromote {
    /**
     *  .
     */
    private String code;

    /**
     * 商品编码 .
     */
    private String productCode;

    /**
     * 开始时间 .
     */
    private Date openDate;

    /**
     * 结束时间 .
     */
    private Date closeDate;

    /**
     * 状态 0:启用  1:停用 .
     */
    private String status;

    /**
     * 创建人 .
     */
    private String creater;

    /**
     * 创建时间 .
     */
    private Date createTime;

    /**
     * 修改人 .
     */
    private String updater;

    /**
     * 修改时间 .
     */
    private Date updateTime;

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
     * 商品编码 .
     *
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 商品编码 .
     *
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * 开始时间 .
     *
     */
    public Date getOpenDate() {
        return openDate;
    }

    /**
     * 开始时间 .
     *
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * 结束时间 .
     *
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * 结束时间 .
     *
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * 状态 0:启用  1:停用 .
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态 0:启用  1:停用 .
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
     * 创建时间 .
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间 .
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人 .
     *
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * 修改人 .
     *
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    /**
     * 修改时间 .
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间 .
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 输出BEAN数据信息
     * @author LeoPeng
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductPromote [code=").append(code);
        builder.append(",productCode=").append(productCode); 
        builder.append(",openDate=").append(openDate); 
        builder.append(",closeDate=").append(closeDate); 
        builder.append(",status=").append(status); 
        builder.append(",creater=").append(creater); 
        builder.append(",createTime=").append(createTime); 
        builder.append(",updater=").append(updater); 
        builder.append(",updateTime=").append(updateTime); 
        builder.append("]");
        return builder.toString();
    }

}