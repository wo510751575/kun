package com.lj.eshop.eis.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.MobilePhoneLoginDto;
import com.lj.eshop.eis.dto.ResponseCode;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.eis.dto.TokenDto;
import com.lj.eshop.eis.service.IUserLoginService;
import com.lj.eshop.eis.utils.AuthCodeUtils;
import com.lj.eshop.emus.CodeCheckBizType;
import com.lj.eshop.emus.MemberStatus;
import com.lj.eshop.service.IMemberService;

/**
 * 
 * @ClassName: MemberController
 * @Description:
 * @author:
 * @date: 2019-11-02 16:39
 * 
 * @Copyright:
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private IMemberService memberService;
	@Autowired
	public IUserLoginService userLoginService;

	@RequestMapping(value = { "register" })
	@ResponseBody
	public ResponseDto register(MemberDto param, String authCode, String imei) {
		if (StringUtils.isEmpty(param.getPhone()) || StringUtils.isEmpty(authCode)
				|| StringUtils.isEmpty(param.getPassword()) || StringUtils.isEmpty(param.getShareCode())
				|| StringUtils.isEmpty(imei)) {
			return ResponseDto.getErrorResponse(ResponseCode.PARAM_ERROR);
		}
		// 校验验证码
		AuthCodeUtils.validAuthCode(param.getPhone(), CodeCheckBizType.REG.getValue(), authCode,
				AuthCodeUtils.AUTH_CODE_VALID_TIME);

		MemberDto shareDto = memberService.findMemberByShareCode(param.getShareCode());
		if (shareDto == null) {
			return ResponseDto.getErrorResponse(ResponseCode.SHOP_NOT_FIND);
		}

		MemberDto memberDto = userLoginService.findMemberDtoByPhone(param.getPhone());
		if (memberDto != null) {
			return ResponseDto.getErrorResponse(ResponseCode.PHONE_EXIST);
		}

		param.setStatus(MemberStatus.NORMAL.getValue());
		param.setMerchantCode(shareDto.getMerchantCode());
		param.setWxNo(imei);
		param.setOfficeId(shareDto.getOfficeId());
		param.setOfficeName(shareDto.getOfficeName());
		memberService.addMember(param);
		return ResponseDto.successResp();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto mobilePhoneLogin(MobilePhoneLoginDto dto) {
		if (StringUtils.isEmpty(dto.getImei())) {
			return ResponseDto.getErrorResponse(ResponseCode.PARAM_ERROR);
		}
		TokenDto tk = userLoginService.mobilePhoneLogin(dto);
		return ResponseDto.successResp(tk);
	}

	/**
	 * 
	 * 根据会员编号获取会员信息
	 */
	@RequestMapping(value = "getByCode")
	@ResponseBody
	public ResponseDto getByCode(String code) {
		AssertUtils.notNullAndEmpty(code, "编号不能为空");
		MemberDto memberDto = new MemberDto();
		memberDto.setCode(code);
		MemberDto dto = memberService.findMember(memberDto);
		return ResponseDto.successResp(dto);
	}

	/**
	 * 获取当前登录会员信息
	 */
	@RequestMapping(value = "get")
	@ResponseBody
	public ResponseDto get() {
		MemberDto memberDto = new MemberDto();
		memberDto.setCode(getLoginMemberCode());
		MemberDto dto = memberService.findMember(memberDto);
		return ResponseDto.successResp(dto);
	}

	/**
	 * 我邀请的会员
	 */
	@RequestMapping(value = "myInvite")
	@ResponseBody
	public ResponseDto myInvite(Integer pageNo, Integer pageSize) {

		return ResponseDto.successResp();
	}
}
