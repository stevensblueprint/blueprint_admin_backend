package com.sitblueprint.admin.service;

import com.sitblueprint.admin.dtos.member.MemberDTO;

import com.sitblueprint.admin.dtos.member.RegistrationRequestDTO;
import java.util.List;

public interface MemberService {
	List<MemberDTO> getAllMembers();

	MemberDTO getMemberById(Long memberId);

	MemberDTO createMember(MemberDTO member);

	MemberDTO updateMember(MemberDTO member);

	void deleteMemberById(Long memberId);

	void enableMemberById(Long memberId);

	void disableMemberById(Long memberId);
}
