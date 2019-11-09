/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.eis.controller.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.eshop.dto.AccountInfoDto;
import com.lj.eshop.dto.FindAccountInfoPage;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.ResponseCode;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.service.IAccountInfoService;

/**
 * 
 * 类说明：收款账户
 * <p>
 * 详细描述：
 * 
 * @Company:
 * @author lhy CreateDate: 2017年9月4日
 */
@RestController
@RequestMapping("/acctInfo")
public class AccountInfoController extends BaseController {

	@Autowired
	IAccountInfoService accountInfoService;

	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseDto list() {
		String mbrCode = getLoginMemberCode();
		FindAccountInfoPage findAccountInfoPage = new FindAccountInfoPage();
		AccountInfoDto param = new AccountInfoDto();
		param.setMbrCode(mbrCode);
		findAccountInfoPage.setParam(param);
		List<AccountInfoDto> list = accountInfoService.findAccountInfos(findAccountInfoPage);
		return ResponseDto.successResp(list);
	}

	@RequestMapping(value = "/get")
	@ResponseBody
	public ResponseDto get(AccountInfoDto param) {
		AccountInfoDto accountInfoDto = accountInfoService.findAccountInfo(param);
		return ResponseDto.successResp(accountInfoDto);
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseDto add(AccountInfoDto param) {
		if (StringUtils.isBlank(param.getAccount()) || StringUtils.isBlank(param.getName())
				|| StringUtils.isBlank(param.getPid()) || StringUtils.isBlank(param.getType())) {
			return ResponseDto.getErrorResponse(ResponseCode.PARAM_ERROR);
		}
		param.setMbrCode(getLoginMemberCode());
		accountInfoService.addAccountInfo(param);
		return ResponseDto.successResp();
	}

	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseDto edit(AccountInfoDto param) {
		if (StringUtils.isBlank(param.getCode())) {
			return ResponseDto.getErrorResponse(ResponseCode.PARAM_ERROR);
		}
		AccountInfoDto accountInfoDto = accountInfoService.findAccountInfo(param);
		if (accountInfoDto == null) {
			return ResponseDto.getErrorResponse("", "数据不存在");
		}
		accountInfoService.updateAccountInfo(param);
		return ResponseDto.successResp();
	}
}
