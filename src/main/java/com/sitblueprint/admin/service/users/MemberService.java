package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();

    Member getMemberById(Long memberId);

    Member createMember(Member member);

    Member updateMember(Member member);

    void deleteMemberById(Long memberId);

    void enableMemberById(Long memberId);

    void disableMemberById(Long memberId);

    void resetPassword(Long memberId, String password);
}
