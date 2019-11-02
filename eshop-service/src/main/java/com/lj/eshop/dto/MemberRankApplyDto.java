package com.lj.eshop.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: MemberRankApplyDto
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 
 * @date: 2019-09-18 18:27
 * 
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved.
 *             注意：本内容仅限于内部传阅，禁止外泄
 */
/**
 * @ClassName: MemberRankApplyDto
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 
 * @date: 2019-09-23 09:43
 * 
 * @Copyright: 2019 www.kehuzhitongche.com Inc. All rights reserved.
 *             注意：本内容仅限于内部传阅，禁止外泄
 */
public class MemberRankApplyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * .
	 */
	private String code;

	/**
	 * 客户code(fk:t_shop.code) .
	 */
	private String memberCode;
	/**
	 * 客户名称
	 */
	private String memberName;

	/**
	 * 会员特权code(fk: t_member_rank.code) .
	 */
	private String memberRankCode;
	/**
	 * 会员特权名称
	 */
	private String memberRankName;

	/**
	 * 申请时间 .
	 */
	private Date applyTime;

	/**
	 * 审核时间 .
	 */
	private Date approveTime;

	/**
	 * 审核状态：0审核中，1审核通过，2审核不通过 .
	 */
	private String status;

	/**
	 * 是否删除（0未删除 1删除） .
	 */
	private String delFlag;
	/**
	 * 会员申请赠送礼包关联code
	 */
	private String giftCode;
	/** 邀请人mbrCode */
	private String myInvite;
	private String avotor;

	public String getAvotor() {
		return avotor;
	}

	public void setAvotor(String avotor) {
		this.avotor = avotor;
	}

	public String getMyInvite() {
		return myInvite;
	}

	public void setMyInvite(String myInvite) {
		this.myInvite = myInvite;
	}

	public String getGiftCode() {
		return giftCode;
	}

	public void setGiftCode(String giftCode) {
		this.giftCode = giftCode;
	}

	/**
	 * @Title: getCode <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @Title: setCode <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @Title: getMemberCode <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getMemberCode() {
		return memberCode;
	}

	/**
	 * @Title: setMemberCode <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	/**
	 * @Title: getMemberName <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @Title: setMemberName <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	/**
	 * @Title: getMemberRankName <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getMemberRankName() {
		return memberRankName;
	}

	/**
	 * @Title: setMemberRankName <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setMemberRankName(String memberRankName) {
		this.memberRankName = memberRankName;
	}

	/**
	 * @Title: getApplyTime <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * @Title: setApplyTime <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * @Title: getApproveTime <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public Date getApproveTime() {
		return approveTime;
	}

	/**
	 * @Title: setApproveTime <BR>
	 * @Description: please write your description <BR>
	 * @return: Date <BR>
	 */
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	/**
	 * @Title: getStatus <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @Title: setStatus <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @Title: getDelFlag <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @Title: setDelFlag <BR>
	 * @Description: please write your description <BR>
	 * @return: String <BR>
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		return "MemberRankApplyDto [code=" + code + ", memberCode=" + memberCode + ", memberName=" + memberName
				+ ", memberRankCode=" + memberRankCode + ", memberRankName=" + memberRankName + ", applyTime="
				+ applyTime + ", approveTime=" + approveTime + ", status=" + status + ", delFlag=" + delFlag
				+ ", giftCode=" + giftCode + ", myInvite=" + myInvite + ", avotor=" + avotor + "]";
	}

}
