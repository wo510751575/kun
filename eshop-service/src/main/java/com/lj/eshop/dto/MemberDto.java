package com.lj.eshop.dto;

import java.io.Serializable;
import java.util.Date;

public class MemberDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1451001239589254781L;

	/** 会员编码 */
	private String code;

	/** 会员名称 */
	private String name;

	/** 手机号码 */
	private String phone;

	/** 微信号 */
	private String wxNo;

	/** 状态 0:正常 2:注销 3冻结 */
	private String status;

	/** 类型 0:供应商 1:开店 2:消费者 3:开店消费者 4:供应商消费者 5:供应商消费者开店 */
	private String type;

	/** 头像 */
	private String avotor;

	/** 省 */
	private String province;

	/** 市 */
	private String city;

	/** 区 */
	private String area;

	/** 我的邀请人 */
	private String myInvite;

	/** 评分等级 1,2,3,4,5 */
	private String grade;

	/** 创建时间 */
	private Date createTime;

	/** 创建人 */
	private String creater;

	/** 微信绑定openId(如果是供应商则绑定的供应商ID) */
	private String openId;

	/** MALE：男 FEMALE：女 */
	private String sex;

	/** 0：扫描 1：分享 */
	private String sourceFrom;

	/** 商户编号(fk:t_merchant.code) */
	private String merchantCode;

	/** 完整的微信号检索条件 */
	private String wxNoAll;

	/** 会员有效期开始日期 */
	private Date openMemberDate;

	/** 会员有效期结束日期 */
	private Date closeMemberDate;

	/** 会员类型code */
	private String memberRankCode;
	/** 会员类型名称 */
	private String memberRankName;

	/** 密码password */
	private String password;
	/**
	 * 邀请人姓名
	 */
	private String myInviteName;
	/**
	 * 邀请人电话
	 */
	private String myInvitePhone;
	/**
	 * 邀请码
	 */
	private String shareCode;

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getMyInviteName() {
		return myInviteName;
	}

	public void setMyInviteName(String myInviteName) {
		this.myInviteName = myInviteName;
	}

	public String getMyInvitePhone() {
		return myInvitePhone;
	}

	public void setMyInvitePhone(String myInvitePhone) {
		this.myInvitePhone = myInvitePhone;
	}

	public String getMemberRankName() {
		return memberRankName;
	}

	public void setMemberRankName(String memberRankName) {
		this.memberRankName = memberRankName;
	}

	/**
	 * @Title: getPassword <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @Title: setPassword <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @Title: getOpenMemberDate <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public Date getOpenMemberDate() {
		return openMemberDate;
	}

	/**
	 * @Title: setOpenMemberDate <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public void setOpenMemberDate(Date openMemberDate) {
		this.openMemberDate = openMemberDate;
	}

	/**
	 * @Title: getCloseMemberDate <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public Date getCloseMemberDate() {
		return closeMemberDate;
	}

	/**
	 * @Title: setCloseMemberDate <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public void setCloseMemberDate(Date closeMemberDate) {
		this.closeMemberDate = closeMemberDate;
	}

	/**
	 * @Title: getMemberRankCode <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getMemberRankCode() {
		return memberRankCode;
	}

	/**
	 * @Title: setMemberRankCode <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setMemberRankCode(String memberRankCode) {
		this.memberRankCode = memberRankCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getWxNo() {
		return wxNo;
	}

	public void setWxNo(String wxNo) {
		this.wxNo = wxNo == null ? null : wxNo.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getAvotor() {
		return avotor;
	}

	public void setAvotor(String avotor) {
		this.avotor = avotor == null ? null : avotor.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getMyInvite() {
		return myInvite;
	}

	public void setMyInvite(String myInvite) {
		this.myInvite = myInvite == null ? null : myInvite.trim();
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade == null ? null : grade.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom == null ? null : sourceFrom.trim();
	}

	/**
	 * @return the merchantCode
	 */
	public String getMerchantCode() {
		return merchantCode;
	}

	/**
	 * @param merchantCode the merchantCode to set
	 */
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	/**
	 * @return the wxNoAll
	 */
	public String getWxNoAll() {
		return wxNoAll;
	}

	/**
	 * @param wxNoAll the wxNoAll to set
	 */
	public void setWxNoAll(String wxNoAll) {
		this.wxNoAll = wxNoAll;
	}

	@Override
	public String toString() {
		return "MemberDto [code=" + code + ", name=" + name + ", phone=" + phone + ", wxNo=" + wxNo + ", status="
				+ status + ", type=" + type + ", avotor=" + avotor + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", myInvite=" + myInvite + ", grade=" + grade + ", createTime=" + createTime
				+ ", creater=" + creater + ", openId=" + openId + ", sex=" + sex + ", sourceFrom=" + sourceFrom
				+ ", merchantCode=" + merchantCode + ", wxNoAll=" + wxNoAll + ", openMemberDate=" + openMemberDate
				+ ", closeMemberDate=" + closeMemberDate + ", memberRankCode=" + memberRankCode + ", memberRankName="
				+ memberRankName + ", password=" + password + ", myInviteName=" + myInviteName + ", myInvitePhone="
				+ myInvitePhone + ", shareCode=" + shareCode + "]";
	}

}
