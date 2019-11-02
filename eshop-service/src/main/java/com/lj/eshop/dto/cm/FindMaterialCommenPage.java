package com.lj.eshop.dto.cm;

import java.util.Date;
import java.util.List;

import com.lj.base.core.pagination.PageParamEntity; 

/**
 * The Class FindMaterialCommenPage.
 */
public class FindMaterialCommenPage extends PageParamEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1713763802974745353L; 
	  /**
     * CODE .
     */
    private String code;

    /**
     * 类型CODE .
     */
    private String materialTypeCode;

    /**
     * 类型名称 .
     */
    private String materialTypeName;

    /**
     * 标题 .
     */
    private String title;

    /**
     * 内容 .
     */
    private String content;

    /**
     * 图片地址 .
     */
    private String imgAddr;
    
    /**
     * 回应数量
     */
    private int respondNum;

    /**
     * 创建人 .
     */
    private String createId;

    /**
     * 创建时间 .
     */
    private Date createDate;
    
    /**
     * 商户编号 .
     */
    private String merchantNo;
    
    
    /**
     * 商户名称 .
     */
    private String merchantName;
    
    /**
     * 门店编号
     */
    private String shopNo;
    /**
     * 门店名称
     */
    private String shopnName;
    /**
     * 维护维度
     */
    private String dimensionSt;
    
    /**
     * code集合
     */
    private List<String> codes;
    

    public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopnName() {
		return shopnName;
	}

	public void setShopnName(String shopnName) {
		this.shopnName = shopnName;
	}

	public String getDimensionSt() {
		return dimensionSt;
	}

	public void setDimensionSt(String dimensionSt) {
		this.dimensionSt = dimensionSt;
	}

	/**
     * Gets the 商户编号 .
     *
     * @return the merchantNo
     */
	public String getMerchantNo() {
		return merchantNo;
	}

	/**
	 * Sets the 商户编号 .
	 *
	 * @param merchantNo the merchantNo to set
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	/**
	 * Gets the 商户名称 .
	 *
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * Sets the 商户名称 .
	 *
	 * @param merchantName the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

    /**
     * CODE .
     *
     * @return the cODE 
     */
    public String getCode() {
        return code;
    }
    
    

    /**
     * CODE .
     *
     * @param code the new cODE 
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 类型CODE .
     *
     * @return the 类型CODE 
     */
    public String getMaterialTypeCode() {
        return materialTypeCode;
    }

    /**
     * 类型CODE .
     *
     * @param materialTypeCode the new 类型CODE 
     */
    public void setMaterialTypeCode(String materialTypeCode) {
        this.materialTypeCode = materialTypeCode == null ? null : materialTypeCode.trim();
    }

    /**
     * 类型名称 .
     *
     * @return the 类型名称 
     */
    public String getMaterialTypeName() {
        return materialTypeName;
    }

    /**
     * 类型名称 .
     *
     * @param materialTypeName the new 类型名称 
     */
    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName == null ? null : materialTypeName.trim();
    }

    /**
     * 标题 .
     *
     * @return the 标题 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题 .
     *
     * @param title the new 标题 
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 内容 .
     *
     * @return the 内容 
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容 .
     *
     * @param content the new 内容 
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 图片地址 .
     *
     * @return the 图片地址 
     */
    public String getImgAddr() {
        return imgAddr;
    }

    /**
     * 图片地址 .
     *
     * @param imgAddr the new 图片地址 
     */
    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr == null ? null : imgAddr.trim();
    }
    
    /**
     * 回应数量 .
     *
     * @return the 回应数量 
     */
    public int getRespondNum() {
		return respondNum;
	}
    
    /**
     * 回应数量 .
     *
     * @param imgAddr the new 回应数量 
     */
	public void setRespondNum(int respondNum) {
		this.respondNum = respondNum;
	}

	/**
     * 创建人 .
     *
     * @return the 创建人 
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 创建人 .
     *
     * @param createId the new 创建人 
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 创建时间 .
     *
     * @return the 创建时间 
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间 .
     *
     * @param createDate the new 创建时间 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    

    public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}

	@Override
	public String toString() {
		return "FindMaterialCommenPage [code=" + code + ", materialTypeCode="
				+ materialTypeCode + ", materialTypeName=" + materialTypeName
				+ ", title=" + title + ", content=" + content + ", imgAddr="
				+ imgAddr + ", respondNum=" + respondNum + ", createId="
				+ createId + ", createDate=" + createDate + ", merchantNo="
				+ merchantNo + ", merchantName=" + merchantName + ", shopNo="
				+ shopNo + ", shopnName=" + shopnName + ", dimensionSt="
				+ dimensionSt + "]";
	}

}
