package com.lj.eshop.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.encryption.MD5;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.constant.ErrorCode;
import com.lj.eshop.constant.NoUtil;
import com.lj.eshop.dao.IAccountDao;
import com.lj.eshop.dao.IMemberDao;
import com.lj.eshop.domain.Account;
import com.lj.eshop.domain.Member;
import com.lj.eshop.dto.FindMemberPage;
import com.lj.eshop.dto.MemberDto;
import com.lj.eshop.emus.AccountStatus;
import com.lj.eshop.emus.AccountType;
import com.lj.eshop.service.IMemberService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 *         CreateDate: 2017-08-22
 */
@Service
public class MemberServiceImpl implements IMemberService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Resource
	private IMemberDao memberDao;
	@Resource
	private IAccountDao accountDao;

	@Override
	public MemberDto addMember(MemberDto memberDto) throws TsfaServiceException {
		logger.debug("addMember(AddMember addMember={}) - start", memberDto);

		AssertUtils.notNull(memberDto);
		try {
			Member member = new Member();
			// add数据录入
			member.setCode(GUID.generateCode());
			member.setName(memberDto.getName());
			member.setPhone(memberDto.getPhone());
			member.setWxNo(memberDto.getWxNo());
			member.setStatus(memberDto.getStatus());
			member.setType(memberDto.getType());
			member.setAvotor(memberDto.getAvotor());
			member.setProvince(memberDto.getProvince());
			member.setCity(memberDto.getCity());
			member.setArea(memberDto.getArea());
			member.setMyInvite(memberDto.getMyInvite());
			member.setGrade(memberDto.getGrade());
			member.setCreateTime(new Date());
			member.setCreater(memberDto.getCreater());
			member.setOpenId(memberDto.getOpenId());
			member.setSex(memberDto.getSex());
			member.setSourceFrom(memberDto.getSourceFrom());
			member.setMerchantCode(memberDto.getMerchantCode());
			member.setMemberRankCode(memberDto.getMemberRankCode());
			member.setPassword(MD5.encryptByMD5(memberDto.getPassword()));
			member.setShareCode(memberDto.getShareCode());
			memberDao.insertSelective(member);
			memberDto.setCode(member.getCode());
			memberDto.setCreateTime(member.getCreateTime());

			// 2：开会员的同时开虚拟账户
			Account account = new Account();
			account.setCode(GUID.generateCode());
			account.setMbrCode(member.getCode());
			account.setCreateTime(new Date());
			account.setStatus(AccountStatus.NORMAL.getValue());
			account.setType(AccountType.ACC.getValue());
			account.setUpdateTime(new Date());
			// 账号accNo生成规则，暂时不存
			account.setAccNo(NoUtil.generateNo(NoUtil.JY));
			accountDao.insertSelective(account);
			logger.debug("addMember(MemberDto) - end - return");
			return memberDto;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增会员信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_ADD_ERROR, "新增会员信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询会员信息
	 *
	 * @param findMemberPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017年07月10日
	 *
	 */
	public List<MemberDto> findMembers(FindMemberPage findMemberPage) throws TsfaServiceException {
		logger.debug("findMembers(MemberDto memberDto={}) - start ", findMemberPage);
		AssertUtils.notNull(findMemberPage);
		List<MemberDto> returnList = null;
		try {
			returnList = memberDao.findMembers(findMemberPage);
		} catch (Exception e) {
			logger.error("查找会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_NOT_EXIST_ERROR, "会员信息不存在");
		}
		logger.debug("findMembers(MemberDto memberDto={}) - end");
		return returnList;
	}

	@Override
	public void updateMember(MemberDto memberDto) throws TsfaServiceException {
		logger.debug("updateMember(MemberDto memberDto={}) - start", memberDto);

		AssertUtils.notNull(memberDto);
		AssertUtils.notNullAndEmpty(memberDto.getCode(), "Code不能为空");
		try {
			Member member = new Member();
			// update数据录入
			member.setCode(memberDto.getCode());
			member.setName(memberDto.getName());
			member.setPhone(memberDto.getPhone());
			member.setWxNo(memberDto.getWxNo());
			member.setStatus(memberDto.getStatus());
			member.setType(memberDto.getType());
			member.setAvotor(memberDto.getAvotor());
			member.setProvince(memberDto.getProvince());
			member.setCity(memberDto.getCity());
			member.setArea(memberDto.getArea());
			member.setMyInvite(memberDto.getMyInvite());
			member.setGrade(memberDto.getGrade());
			member.setCreateTime(memberDto.getCreateTime());
			member.setCreater(memberDto.getCreater());
			member.setOpenId(memberDto.getOpenId());
			member.setSex(memberDto.getSex());
			member.setSourceFrom(memberDto.getSourceFrom());
			member.setMerchantCode(memberDto.getMerchantCode());
			member.setPassword(memberDto.getPassword());
			member.setOpenMemberDate(memberDto.getOpenMemberDate());
			member.setCloseMemberDate(memberDto.getCloseMemberDate());
			member.setMemberRankCode(memberDto.getMemberRankCode());
			member.setPassword(memberDto.getPassword());
			member.setShareCode(memberDto.getShareCode());
			AssertUtils.notUpdateMoreThanOne(memberDao.updateByPrimaryKeySelective(member));
			logger.debug("updateMember(MemberDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("会员信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_UPDATE_ERROR, "会员信息更新信息错误！", e);
		}
	}

	@Override
	public MemberDto findMember(MemberDto memberDto) throws TsfaServiceException {
		logger.debug("findMember(FindMember findMember={}) - start", memberDto);

		AssertUtils.notNull(memberDto);
		AssertUtils.notAllNull(memberDto.getCode(), "Code不能为空");
		try {
			Member member = memberDao.selectByPrimaryKey(memberDto.getCode());
			if (member == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.MEMBER_NOT_EXIST_ERROR,"会员信息不存在");
			}
			MemberDto findMemberReturn = new MemberDto();
			// find数据录入
			findMemberReturn.setCode(member.getCode());
			findMemberReturn.setName(member.getName());
			findMemberReturn.setPhone(member.getPhone());
			findMemberReturn.setWxNo(member.getWxNo());
			findMemberReturn.setStatus(member.getStatus());
			findMemberReturn.setType(member.getType());
			findMemberReturn.setAvotor(member.getAvotor());
			findMemberReturn.setProvince(member.getProvince());
			findMemberReturn.setCity(member.getCity());
			findMemberReturn.setArea(member.getArea());
			findMemberReturn.setMyInvite(member.getMyInvite());
			findMemberReturn.setGrade(member.getGrade());
			findMemberReturn.setCreateTime(member.getCreateTime());
			findMemberReturn.setCreater(member.getCreater());
			findMemberReturn.setOpenId(member.getOpenId());
			findMemberReturn.setSex(member.getSex());
			findMemberReturn.setSourceFrom(member.getSourceFrom());
			findMemberReturn.setMerchantCode(member.getMerchantCode());
			findMemberReturn.setOpenMemberDate(member.getOpenMemberDate());
			findMemberReturn.setCloseMemberDate(member.getCloseMemberDate());
			findMemberReturn.setMemberRankCode(member.getMemberRankCode());
			findMemberReturn.setMemberRankName(member.getMemberRankName());
			findMemberReturn.setPassword(member.getPassword());
			findMemberReturn.setShareCode(member.getShareCode());
			logger.debug("findMember(MemberDto) - end - return value={}", findMemberReturn);
			return findMemberReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_FIND_ERROR, "查找会员信息信息错误！", e);
		}

	}

	@Override
	public Page<MemberDto> findMemberPage(FindMemberPage findMemberPage) throws TsfaServiceException {
		logger.debug("findMemberPage(FindMemberPage findMemberPage={}) - start", findMemberPage);

		AssertUtils.notNull(findMemberPage);
		List<MemberDto> returnList = null;
		int count = 0;
		try {
			count = memberDao.findMemberPageCount(findMemberPage);
			if (count > 0) {
				returnList = memberDao.findMemberPage(findMemberPage);
			}
		} catch (Exception e) {
			logger.error("会员信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_FIND_PAGE_ERROR, "会员信息不存在错误.！", e);
		}
		Page<MemberDto> returnPage = new Page<MemberDto>(returnList, count, findMemberPage);

		logger.debug("findMemberPage(FindMemberPage) - end - return value={}", returnPage);
		return returnPage;
	}

	@Override
	public List<String> findMemberCodesByInvite(String myInvite) throws TsfaServiceException {
		logger.debug("findMemberCodesByInvite(String myInvite={}) - start ", myInvite);
		AssertUtils.notNullAndEmpty(myInvite, "邀请人不能为空");
		List<String> returnList = null;
		try {
			returnList = memberDao.findMemberCodesByInvite(myInvite);
		} catch (Exception e) {
			logger.error("查找会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_NOT_EXIST_ERROR, "会员信息不存在");
		}
		logger.debug("findMembers(MemberDto memberDto={}) - end");
		return returnList;
	}

	@Override
	public MemberDto findMemberByShareCode(String shareCode) throws TsfaServiceException {
		logger.debug("findMemberByShareCode(String shareCode={}) - start", shareCode);

		AssertUtils.notNullAndEmpty(shareCode, "邀请码不能为空");
		try {
			MemberDto member = memberDao.findMemberByShareCode(shareCode);
			if (member == null) {
				return null;
			}
			logger.debug("findMemberByShareCode(MemberDto) - end - return value={}", member);
			return member;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找会员信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.MEMBER_FIND_ERROR, "查找会员信息信息错误！", e);
		}
	}

}
