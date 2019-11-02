package com.lj.eoms.dto;

import java.io.Serializable;

import com.lj.eoms.utils.excel.annotation.ExcelField;


/**
 * 
 * 
 * 类说明:门店会员
 *  
 * 
 * <p>
 * 详细描述:
 *   
 * @Company: 小坤有限公司
 * @author 林进权
 *   
 * CreateDate: 2017年9月2日
 */
public class ShopMemberDto implements Serializable{
	
	

	


	/**   
	 * <p>Title: toString</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see java.lang.Object#toString()   
	 */
	@Override
	public String toString() {
		return "ShopMemberDto [memberName=" + memberName + ", mobile=" + mobile + ", sex=" + sex + ", wxNo=" + wxNo
				+ ", province=" + province + ", city=" + city + ", area=" + area + ", shopOpenTime=" + shopOpenTime
				+ ", shopCountry=" + shopCountry + ", shopAddres=" + shopAddres + ", memberRankCode=" + memberRankCode
				+ "]";
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196432L;

	
	 /**
     * 客户名称
     */
	@ExcelField(title="客户名称", align=2, sort=10)
    private String memberName;
	
	 /**
     * 手机
     */
	@ExcelField(title="手机(号码前加上英文'符号)", align=3, sort=20)
    private String mobile;
	
	/**
     * 性别
     * 男.
		MALE("男"),
	 ** 女. *
		FEMALE("女");
     */
	@ExcelField(title="性别(男,女)", align=2, sort=30)
    private String sex;

	/**
	 * 微信號
	 */
	@ExcelField(title="微信号", align=2, sort=40)
	private String wxNo;
	
	/** 省*/
	@ExcelField(title="省", align=2, sort=50)
    private String province;

    /** 市*/
	@ExcelField(title="市", align=2, sort=60)
    private String city;

    /** 区*/
	@ExcelField(title="区", align=2, sort=70)
    private String area;

	/** 开店日期*/
	@ExcelField(title="开店日期", align=2, sort=80)
	private String shopOpenTime;

	/** 地 区*/
	@ExcelField(title="地 区", align=2, sort=90)
	private String shopCountry;
	
	/** 详细地址*/
	@ExcelField(title="详细地址", align=2, sort=100)
	private String shopAddres;
	
	/** 详细地址*/
	@ExcelField(title="会员等级(请填写对应数字:1-白银会员;2-黄金会员;3-白金会员;4-钻石会员)", align=2, sort=100)
	private String memberRankCode;
    
	
	

	
	/**  
	 * @Title:  getMemberRankCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public String getMemberRankCode() {
		return memberRankCode;
	}


	/**  
	 * @Title:  setMemberRankCode <BR>  
	 * @Description: please write your description <BR>  
	 * @return: String <BR>  
	 */
	public void setMemberRankCode(String memberRankCode) {
		this.memberRankCode = memberRankCode;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getSex() {
//		if(!StringUtils.isEmpty(sex)) {
//			Gender[] genders = Gender.values();
//			for (int i = 0; i < genders.length; i++) {
//				Gender gender = genders[i];
//				if(gender.getName().equals(sex))
//					return gender.getValue();
//			}
//		}
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getWxNo() {
		return wxNo;
	}


	public void setWxNo(String wxNo) {
		this.wxNo = wxNo;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getShopOpenTime() {
		return shopOpenTime;
	}


	public void setShopOpenTime(String shopOpenTime) {
		this.shopOpenTime = shopOpenTime;
	}


	public String getShopCountry() {
		return shopCountry;
	}


	public void setShopCountry(String shopCountry) {
		this.shopCountry = shopCountry;
	}


	public String getShopAddres() {
		return shopAddres;
	}


	public void setShopAddres(String shopAddres) {
		this.shopAddres = shopAddres;
	}
	
}
