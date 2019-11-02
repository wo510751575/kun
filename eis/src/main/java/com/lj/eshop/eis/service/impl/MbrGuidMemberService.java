/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.eis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.dto.FindShopPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.dto.ShopDto;
import com.lj.eshop.eis.dto.MobilePhoneLoginDto;
import com.lj.eshop.eis.dto.ResponseCode;
import com.lj.eshop.eis.utils.AuthCodeUtils;
import com.lj.eshop.emus.CodeCheckBizType;
import com.lj.eshop.service.IMemberService;
import com.lj.eshop.service.IMerchantService;
import com.lj.eshop.service.IShopService;

/**
 * 
 * 类说明：导购同步。
 * 
 * 
 * <p>
 * 
 * @Company: 
 * @author lhy
 * 
 *         CreateDate: 2017年9月20日
 */
@Service
public class MbrGuidMemberService {
	private static Logger logger = LoggerFactory.getLogger(MbrGuidMemberService.class);

	@Autowired
	private IShopService shopService;
//	@Autowired
//	private IGuidMemberService guidMemberService;//导购信息
	@Autowired
	private IMemberService memberService;
//	@Autowired
//	private com.lj.business.member.service.IShopService mbrShopService;//会员体系店铺信息
	@Autowired
	private IMerchantService merchantService;

	/**
	 *
	 * 方法说明：支付成功后同步导购信息。
	 *
	 * @param ecShopNo 电商系统shop.shop_no
	 *
	 * @author lhy 2017年9月20日
	 *
	 */
	@Deprecated
	public void addGuidMember(String ecShopNo) {
		FindShopPage findShopPage = new FindShopPage();
		ShopDto shopDto = new ShopDto();
		shopDto.setShopNo(ecShopNo);
		findShopPage.setParam(shopDto);
		List<ShopDto> shopList = shopService.findShops(findShopPage);
		if (shopList != null && shopList.size() > 0) {
			addGuidMember(shopList.get(0));
		} else {
			logger.error("找不到店铺，无法同步导购，shop.shop_no=" + ecShopNo);
		}
	}

	/**
	 * 方法说明：新增导购。
	 * 
	 * @param shopDto.code 必填
	 * @author lhy 2017年9月20日
	 *
	 */
	private void addGuidMember(ShopDto shopDto) {
		ShopDto ecfindShop = shopService.findShop(shopDto);
		// 1.根据店找到电商会员
		MemberDto mbrParam = new MemberDto();
		mbrParam.setCode(ecfindShop.getMbrCode());
		MemberDto ecfindMbr = memberService.findMember(mbrParam);
		// 2.根据电商商户找到会员体系shop
		/*
		 * FindShop findShop = new FindShop();
		 * findShop.setMemberNoMerchant(UserUtils.getUser().getMerchant().getOfficeId())
		 * ; FindShopPageReturn mbrShop =
		 * mbrShopService.findShopByMerchantNo_ec(findShop); MerchantDto mcParam = new
		 * MerchantDto(); mcParam.setCode(shopDto.getCode()); MerchantDto ecMerchant =
		 * merchantService.findMerchant(mcParam); AddGuidMember addGuidMember = new
		 * AddGuidMember(); addGuidMember.setMemberNo(ecfindMbr.getCode());
		 * addGuidMember.setMemberName(ecfindMbr.getName());
		 * addGuidMember.setShopNo(mbrShop.getShopNo());
		 * addGuidMember.setShopName(mbrShop.getShopName());
		 * addGuidMember.setMerchantNo(ecMerchant.getOfficeId());
		 * addGuidMember.setMerchantName(ecMerchant.getMerchantName());
		 * addGuidMember.setStatus(MemberStatus.NORMAL.toString()); //
		 * addGuidMember.setJobNum(); addGuidMember.setMobile(ecfindMbr.getPhone());
		 * addGuidMember.setImei(ecfindShop.getMimeCode()); //
		 * addGuidMember.setEmail(findMbr);
		 * addGuidMember.setNickName(ecfindMbr.getName()); //
		 * addGuidMember.setAreaCode(); // addGuidMember.setAreaName();
		 * addGuidMember.setNoWx(ecfindMbr.getWxNo()); //
		 * addGuidMember.setProvinceCode(); // addGuidMember.setCityCode(); //
		 * addGuidMember.setCityAreaCode(); // addGuidMember.setAddress(); //
		 * addGuidMember.setAge(); if (StringUtils.isNotEmpty(ecfindMbr.getSex())) {
		 * addGuidMember.setGender(ecfindMbr.getSex().equals(MemberSex.MALE.getValue())
		 * ? Gender.MALE.toString() : Gender.FEMALE.toString()); }
		 * addGuidMember.setHeadAddress(ecfindMbr.getAvotor()); //
		 * addGuidMember.setWorkDate(); // addGuidMember.setQcord();
		 * addGuidMember.setCreateId(UserUtils.getUser().getId());
		 * addGuidMember.setRemark("微信支付成功注册"); String pwd = "123456"; if
		 * (StringUtils.isNotEmpty(ecfindMbr.getPhone()) &&
		 * ecfindMbr.getPhone().length() > 5) { pwd =
		 * ecfindMbr.getPhone().substring(ecfindMbr.getPhone().length() - 6); }
		 * addGuidMember.setPwd(MD5.encryptByMD5(MD5.encryptByMD5(pwd)));// 2次Md5加密存贮
		 * guidMemberService.addGuidMember_ec(addGuidMember);
		 */
	}

	/**
	 * 方法说明： 导购找回密码。
	 * 
	 * @param pwdDto
	 *
	 * @author lhy 2017年9月22日
	 *
	 */
	@Deprecated
	public void findLoginPwd(MobilePhoneLoginDto pwdDto) {
		if (StringUtils.isEmpty(pwdDto.getPassword()) || StringUtils.isEmpty(pwdDto.getAuthCode())
				|| StringUtils.isEmpty(pwdDto.getPassword())) {
			throw new TsfaServiceException(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMsg());
		}
		// 1.校验验证码
		AuthCodeUtils.validAuthCode(pwdDto.getMobilePhone(), CodeCheckBizType.GET_LOGIN_PWD.getValue(),
				pwdDto.getAuthCode(), AuthCodeUtils.AUTH_CODE_VALID_TIME);
		// 2.根据手机号找导购
		/*
		 * FindGuidMemberReturn guidMember =
		 * guidMemberService.findGuidMemberByMoblie_ec(pwdDto.getMobilePhone()); if
		 * (guidMember == null) { throw new
		 * TsfaServiceException(ResponseCode.GUID_MEMBER_NOT_EXIST.getCode(),
		 * ResponseCode.GUID_MEMBER_NOT_EXIST.getMsg()); } // 3.修改密码 UpdatePwdDto
		 * updatePwdDto = new UpdatePwdDto();
		 * updatePwdDto.setCode(guidMember.getCode());
		 * updatePwdDto.setPwd(pwdDto.getPassword());
		 * guidMemberService.updatePwd(updatePwdDto);
		 */
	}

	/**
	 * 方法说明：APP修改个人信息。
	 * <p>
	 * 修改会员&同步修改导购。
	 * 
	 * @param updMbr
	 * @author lhy 2017年9月21日
	 *
	 */
	/*
	 * @Deprecated public void updateMember(UpdateGuidMemberDto updMbr) { // 姓名 性别
	 * 邮箱 地区 头像 if (StringUtils.isEmpty(updMbr.getMemberName()) &&
	 * StringUtils.isEmpty(updMbr.getGender()) &&
	 * StringUtils.isEmpty(updMbr.getEmail()) &&
	 * StringUtils.isEmpty(updMbr.getProvinceCode()) &&
	 * StringUtils.isEmpty(updMbr.getCityCode()) &&
	 * StringUtils.isEmpty(updMbr.getCityAreaCode()) &&
	 * StringUtils.isEmpty(updMbr.getHeadAddress()) &&
	 * StringUtils.isEmpty(updMbr.getAddress())) { throw new
	 * TsfaServiceException(ResponseCode.PARAM_ERROR.getCode(),
	 * ResponseCode.PARAM_ERROR.getMsg()); } LoginUserDto user =
	 * UserTokenThreadLocal.get();
	 * 
	 * String sex = null; if (StringUtils.isNotEmpty(updMbr.getGender()) ||
	 * StringUtils.isNotEmpty(updMbr.getMemberName()) ||
	 * StringUtils.isNotEmpty(updMbr.getHeadAddress())) { if (updMbr.getGender() !=
	 * null && (updMbr.getGender().equals(MemberSex.MALE.getValue()) ||
	 * updMbr.getGender().equals(MemberSex.FEMALE.getValue()))) { sex =
	 * updMbr.getGender(); } MemberDto member = new MemberDto();
	 * member.setCode(user.getMember().getCode()); member.setSex(sex);
	 * member.setAvotor(updMbr.getHeadAddress());
	 * member.setName(updMbr.getMemberName()); memberService.updateMember(member);//
	 * 1.修改电商数据 }
	 * 
	 * // 2.修改导购数据 if (user.getGuidMbr() != null) { UpdateGuidMember updateGuidMbr =
	 * new UpdateGuidMember(); updateGuidMbr.setMemberName(updMbr.getMemberName());
	 * updateGuidMbr.setCode(user.getGuidMbr().getCode());
	 * updateGuidMbr.setNickName(updMbr.getMemberName());
	 * updateGuidMbr.setGender(sex); updateGuidMbr.setUpdateDate(new Date());
	 * updateGuidMbr.setUpdateId(user.getMember().getCode());
	 * updateGuidMbr.setProvinceCode(updMbr.getProvinceCode());
	 * updateGuidMbr.setCityCode(updMbr.getCityCode());
	 * updateGuidMbr.setCityAreaCode(updMbr.getCityAreaCode()); //
	 * updateGuidMbr.setAreaCode(updMbr.getAreaCode());区域CODE 暂不处理 //
	 * updateGuidMbr.setAreaName(updMbr.);
	 * updateGuidMbr.setAddress(updMbr.getAddress());
	 * updateGuidMbr.setEmail(updMbr.getEmail());
	 * updateGuidMbr.setHeadAddress(updMbr.getHeadAddress());
	 * logger.info("getHeadAddress:" + updMbr.getHeadAddress());
	 * guidMemberService.updateGuidMember(updateGuidMbr); } }
	 */

}
