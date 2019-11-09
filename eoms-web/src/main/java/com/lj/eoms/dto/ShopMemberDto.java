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
 * @Company:
 * @author 林进权
 * 
 *         CreateDate: 2017年9月2日
 */
public class ShopMemberDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8475436664919196432L;

	/**
	 * 客户名称
	 */
	@ExcelField(title = "客户名称", align = 2, sort = 10)
	private String memberName;

	/**
	 * 手机
	 */
	@ExcelField(title = "手机(号码前加上英文'符号)", align = 3, sort = 20)
	private String mobile;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "ShopMemberDto [memberName=" + memberName + ", mobile=" + mobile + "]";
	}

}
