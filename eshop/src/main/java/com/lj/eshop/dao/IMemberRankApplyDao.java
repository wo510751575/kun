package com.lj.eshop.dao;

import java.util.List;

import com.lj.eshop.domain.MemberRankApply;
import com.lj.eshop.dto.FindMemberRankApplyPage;
import com.lj.eshop.dto.MemberRankApplyDto;

public interface IMemberRankApplyDao {
	int deleteByPrimaryKey(String code);

	int insertSelective(MemberRankApply record);

	MemberRankApply selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(MemberRankApply record);

	List<MemberRankApplyDto> findMemberRankApplys(FindMemberRankApplyPage findMemberRankApplyPage);

	List<MemberRankApplyDto> findMemberRankApplyPage(FindMemberRankApplyPage findMemberRankApplyPage);

	int findMemberRankApplyPageCount(FindMemberRankApplyPage findMemberRankApplyPage);

	int updateByPkAndStatus(MemberRankApply memberRankApply);

	List<MemberRankApplyDto> findMemberByMyInvitePage(FindMemberRankApplyPage findMemberRankApplyPage);

	List<String> findMemberCodesByInvite(String myInvite);
}