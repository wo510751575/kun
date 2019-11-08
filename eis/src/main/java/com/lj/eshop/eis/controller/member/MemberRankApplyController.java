/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
package com.lj.eshop.eis.controller.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.eshop.dto.AccWaterDto;
import com.lj.eshop.dto.AccountDto;
import com.lj.eshop.dto.FindAccWaterPage;
import com.lj.eshop.dto.FindMemberRankApplyPage;
import com.lj.eshop.dto.FindMemberRankPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.dto.MemberRankApplyDto;
import com.lj.eshop.dto.MemberRankDto;
import com.lj.eshop.eis.controller.BaseController;
import com.lj.eshop.eis.dto.ResponseCode;
import com.lj.eshop.eis.dto.ResponseDto;
import com.lj.eshop.emus.AccWaterAccType;
import com.lj.eshop.emus.AccWaterBizType;
import com.lj.eshop.emus.AccWaterPayType;
import com.lj.eshop.emus.AccWaterSource;
import com.lj.eshop.emus.AccWaterStatus;
import com.lj.eshop.emus.AccWaterType;
import com.lj.eshop.emus.DelFlag;
import com.lj.eshop.emus.MemberRankApplyStatus;
import com.lj.eshop.emus.MemberRankMaxRank;
import com.lj.eshop.emus.MemberRankValid;
import com.lj.eshop.service.IAccWaterService;
import com.lj.eshop.service.IAccountService;
import com.lj.eshop.service.IMemberRankApplyService;
import com.lj.eshop.service.IMemberRankService;
import com.lj.eshop.service.IMemberService;

/**
 * 
 * 类说明：我的特权
 * 
 * <p>
 * 
 * @Company:
 * @author 林进权
 * 
 *         CreateDate: 2017年9月1日
 */
@RestController
@RequestMapping("/member/memberRankApply")
public class MemberRankApplyController extends BaseController {

	@Autowired
	private IMemberRankApplyService memberRankApplyService;
	@Autowired
	private IMemberRankService memberRankService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccWaterService accWaterService;
	@Autowired
	private IMemberService memberService;

	/**
	 * 方法说明： 会员特权列表()
	 * 
	 * @author 林进权
	 * 
	 *         CreateDate: 2017年9月1日
	 */
	@RequestMapping(value = { "list" })
	@ResponseBody
	public ResponseDto list() {
		logger.debug("MemberRankController --> list() - start");

		FindMemberRankPage findMemberRankPage = new FindMemberRankPage();
		MemberRankDto dto = new MemberRankDto();
		dto.setDelFlag("0");
		findMemberRankPage.setParam(dto);
		List<MemberRankDto> list = memberRankService.findMemberRanks(findMemberRankPage);
		logger.debug("MemberRankController --> returnMap(={}) - returnMap");
		return ResponseDto.successResp(list);
	}

	/**
	 * 查询一年内的特权购买流水 方法说明：
	 *
	 * @param @return 设定文件
	 * @return List<AccWaterDto> 返回类型
	 * @throws Exception
	 *
	 * @author 林进权 CreateDate: 2017年9月27日
	 */
	private List<AccWaterDto> findAccwatersByRank() {
		AccountDto accountDto = accountService.findAccountByMbrCode(getLoginMemberCode());
		FindAccWaterPage findAccWaterPage = new FindAccWaterPage();
		AccWaterDto accWaterDto = new AccWaterDto();
		accWaterDto.setAccDate(new Date());
		accWaterDto.setAccType(AccWaterAccType.AUTO.getValue());
		accWaterDto.setAccSource(AccWaterSource.ORDER.getValue());
		accWaterDto.setStatus(AccWaterStatus.SUCCESS.getValue());
		accWaterDto.setBizType(AccWaterBizType.PAYMENT.getValue());
		accWaterDto.setWaterType(AccWaterType.SUBTRACT.getValue());
		accWaterDto.setPayType(AccWaterPayType.RANK.getValue());
		accWaterDto.setAccCode(accountDto.getCode());

		// 一年内的购买记录
		Date endDate = new Date();
		Date startDate = DateUtils.addYears(endDate, -1);
		findAccWaterPage.setStartTime(startDate);
		findAccWaterPage.setEndTime(endDate);

		findAccWaterPage.setParam(accWaterDto);
		return accWaterService.findAccWaters(findAccWaterPage);
	}

	/**
	 * 方法说明： 我的特权-申请-付款
	 * 
	 * @param 会员特权编码 memberRankCode 必填
	 * @author 林进权 CreateDate: 2017年9月1日
	 */
	@RequestMapping(value = { "apply" })
	@ResponseBody
	public ResponseDto apply(MemberRankApplyDto memberRankApplyDto, String payAmt, String payType) {

		logger.debug("MemberRankControl --> apply() - start={}", memberRankApplyDto);

		// 基础校验
		if (memberRankApplyDto == null || StringUtils.isEmpty(memberRankApplyDto.getMemberRankCode())) {
			return ResponseDto.createResp(false, ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMsg(),
					null);
		}

		// 校验特权是否存在
		MemberRankDto memberRankDto = new MemberRankDto();
		memberRankDto.setCode(memberRankApplyDto.getMemberRankCode());
		memberRankDto = memberRankService.findMemberRank(memberRankDto);
		if (memberRankDto == null) {
			return ResponseDto.createResp(false, ResponseCode.MEMBER_RANK_APPLY_NOT_FOUND.getCode(),
					ResponseCode.MEMBER_RANK_APPLY_NOT_FOUND.getMsg(), null);
		}

		// 如果已经申请过同级特权
		FindMemberRankApplyPage findMemberRankApplyPage = new FindMemberRankApplyPage();
		MemberRankApplyDto param = new MemberRankApplyDto();
		param.setMemberCode(getLoginMemberCode());
		param.setDelFlag(DelFlag.N.getValue());
		param.setMemberRankCode(memberRankDto.getCode());
		findMemberRankApplyPage.setParam(param);
		List<MemberRankApplyDto> list = memberRankApplyService.findMemberRankApplys(findMemberRankApplyPage);
		if (null != list && list.size() > 0) {
			MemberRankApplyDto delDto = list.get(0);
			if (MemberRankApplyStatus.WAIT.getValue().equals(delDto.getStatus())) {
				return ResponseDto.getErrorResponse("0", "已申请，请等待审核！");
			} else {
				return ResponseDto.getErrorResponse("0", "已缴纳该级别押金！");
			}
		}

		// 添加申请纪录
		MemberDto memberDto = new MemberDto();
		memberDto.setCode(getLoginMemberCode());
		MemberDto member = memberService.findMember(memberDto);
		memberRankApplyDto.setMemberRankName(memberRankDto.getName());
		memberRankApplyDto.setMemberName(member.getName());
		memberRankApplyDto.setMemberCode(member.getCode());
		memberRankApplyDto.setStatus(MemberRankApplyStatus.WAIT.getValue());
		MemberRankApplyDto rDto = memberRankApplyService.addMemberRankApply(memberRankApplyDto);

		logger.debug("MemberRankController --> apply(={}) - end");
		Map<String, String> map = new HashMap<String, String>();
		map.put("applyCode", rDto.getCode());
		map.put("merchantCode", getLoginMerchantCode());
		return ResponseDto.successResp(map);
	}

	/**
	 * 校验特权是否有效 方法说明：
	 *
	 * @param @param shopDto
	 * @param @param map 设定文件
	 * @return void 返回类型
	 * @throws Exception
	 *
	 * @author 林进权 CreateDate: 2017年9月27日
	 */
	private void checkValidRank(MemberDto member, Map<String, Object> map) {
		map.put("validRank", MemberRankValid.INVALID.getValue());
		// 白银会员通过时间判断会员是否有效,其他会员通过是否有预付款判断会员是否有效
		if ("1".equals(map.get("rankCode"))) {
			if (null != member.getCloseMemberDate() && member.getCloseMemberDate().getTime() > (new Date()).getTime()) {
				map.put("validRank", MemberRankValid.VALID.getValue());
			}
		} else {
			// 预付款大于0,会员有效
			if (null != map.get("rankCashAmt") && Double.parseDouble(map.get("rankCashAmt").toString()) > 0) {
				map.put("validRank", MemberRankValid.VALID.getValue());
			}
		}

	}

	/**
	 * 校验最大特权 方法说明：
	 *
	 * @param @param memberRankDto
	 * @param @param map 设定文件
	 * @return void 返回类型
	 * @throws Exception
	 *
	 * @author 林进权 CreateDate: 2017年9月27日
	 */
	private void checkMaxRank(MemberRankDto memberRankDto, Map<String, Object> map) {
		FindMemberRankPage findMemberRankPage = new FindMemberRankPage();
		MemberRankDto dto = new MemberRankDto();
		dto.setDelFlag("0");
		findMemberRankPage.setParam(dto);
		List<MemberRankDto> memberRankDtos = memberRankService.findMemberRanks(findMemberRankPage);
		int gtCnt = 0;
		for (MemberRankDto memberRankDto2 : memberRankDtos) {
			if (memberRankDto.getAmount().compareTo(memberRankDto2.getAmount()) >= 0) {
				gtCnt++;
			}
		}

		map.put("isMax", MemberRankMaxRank.NOT_MAX.getValue());
		if (gtCnt == memberRankDtos.size()) {
			map.put("isMax", MemberRankMaxRank.MAX.getValue());
		}
	}

}
