package com.lj.eshop.dto.cm;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明：
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @Company: 深圳扬恩科技有限公司
 * @author 罗书明
 * 
 * CreateDate: 2017年6月21日
 */  
public class FindMaterialTypePageReturn implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5830696277507250054L; 
    /**
     * CODE .
     */
    private String code;

    /**
     * 商户编号 .
     */
    private String merchantNo;

    /**
     * 导购编号 .
     */
    private String memberNoGm;

    /**
     * 导购姓名 .
     */
    private String memberNameGm;

    /**
     * 类型名称 .
     */
    private String typeName;

    /**
     * 创建人 .
     */
    private String createId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间 .
     */
    private Date createDate;
    
	/**
	 * 类型总数量
	 */
	private Integer typeCount;

	/**
	 * 图片地址
	 */
	private String imgAddr;

	/**
	 * 客户关注度
	 */
	private Double customerAttentionRate;
	
	/**
	 * 网址链接
	 */
    private String linkUrl;


    /**
     * 素材维度\r\n            商户：MERCHANT\r\n            门店：SHOP
     */
    private String materialDimension;
    
    
    

    public String getMaterialDimension() {
		return materialDimension;
	}


	public void setMaterialDimension(String materialDimension) {
		this.materialDimension = materialDimension;
	}
    
    public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
     * 商户编号 .
     *
     * @return the 商户编号 
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 商户编号 .
     *
     * @param merchantNo the new 商户编号 
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    /**
     * 导购编号  .
     *
     * @return the 导购编号  
     */
    public String getMemberNoGm() {
        return memberNoGm;
    }

    /**
     * 导购编号  .
     *
     * @param memberNoGm the new 导购编号  
     */
    public void setMemberNoGm(String memberNoGm) {
        this.memberNoGm = memberNoGm == null ? null : memberNoGm.trim();
    }

    /**
     * 导购姓名 .
     *
     * @return the 导购姓名 
     */
    public String getMemberNameGm() {
        return memberNameGm;
    }

    /**
     * 导购姓名 .
     *
     * @param memberNameGm the new 导购姓名 
     */
    public void setMemberNameGm(String memberNameGm) {
        this.memberNameGm = memberNameGm == null ? null : memberNameGm.trim();
    }

    /**
     * 类型名称 .
     *
     * @return the 类型名称 
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 类型名称 .
     *
     * @param typeName the new 类型名称 
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
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
    

    public Integer getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(Integer typeCount) {
		this.typeCount = typeCount;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public Double getCustomerAttentionRate() {
		return customerAttentionRate;
	}

	public void setCustomerAttentionRate(Double customerAttentionRate) {
		this.customerAttentionRate = customerAttentionRate;
	}

	@Override
	public String toString() {
		return "FindMaterialTypePageReturn [code=" + code + ", merchantNo="
				+ merchantNo + ", memberNoGm=" + memberNoGm + ", memberNameGm="
				+ memberNameGm + ", typeName=" + typeName + ", createId="
				+ createId + ", remark=" + remark + ", createDate="
				+ createDate + ", typeCount=" + typeCount + ", imgAddr="
				+ imgAddr + ", customerAttentionRate=" + customerAttentionRate
				+ ", linkUrl=" + linkUrl + "]";
	}
}
