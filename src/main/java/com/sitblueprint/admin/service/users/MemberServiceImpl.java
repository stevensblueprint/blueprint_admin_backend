package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.AuthMember;
import com.sitblueprint.admin.model.users.Member;
import com.sitblueprint.admin.repository.users.MemberRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberRepository memberRepository;
    private final AuthApiService authApiService;

    public MemberServiceImpl(MemberRepository memberRepository, AuthApiService authApiService) {
        this.memberRepository = memberRepository;
        this.authApiService = authApiService;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("Member with id " + memberId + " was not found")
        );
    }

    @Override
    public Member createMember(Member member) {
        member.setDateJoined(LocalDate.now());
        AuthMember AuthMember = new AuthMember(member);
        try {
            authApiService.createAuthMember(AuthMember);
        } catch (Exception e) {
            log.error("Failed to create Auth user for username: {}", member.getUsername(), e.getMessage());
            throw new RuntimeException("Auth API failed to create member " + member.getUsername());
        }
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member member) {
        try {
            authApiService.updateAuthMember(new AuthMember(member));
        } catch (Exception e) {
            throw new RuntimeException("Auth API failed to update member " + member.getUsername());
        }
        return memberRepository.saveAndFlush(member);
    }

    @Transactional
    @Override
    public void deleteMemberById(Long memberId) {
        Optional<Member> optionalMemberToDelete = memberRepository.findById(memberId);
        if (optionalMemberToDelete.isEmpty()) {
            throw new RuntimeException("Member with id " + memberId + " was not found");
        }
        Member memberToDelete = optionalMemberToDelete.get();
        try {
            authApiService.deleteAuthMember(memberToDelete.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Auth API failed to delete member " + memberToDelete.getId());
        }
        memberRepository.deleteById(memberId);
    }

    @Override
    public void enableMemberById(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found with id " + memberId);
        }
        Member member = memberOptional.get();
        try {
            authApiService.enableAuthMember(member.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Authentication API failed to enable member " + member.getUsername());
        }
        member.setActive(true);
        memberRepository.save(member);
    }

    @Override
    public void disableMemberById(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found with id " + memberId);
        }
        Member member = memberOptional.get();
        try {
            authApiService.disableAuthMember(member.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Authentication API failed to disable member " + member.getUsername());
        }
        member.setActive(false);
        memberRepository.save(member);
    }

    @Override
    public void resetPassword(Long memberId, String newPassword) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found with id " + memberId);
        }
        Member member = memberOptional.get();
        member.setPassword(newPassword);
        try {
            authApiService.resetPasswordAuthMember(member.getUsername(), newPassword);
        } catch (Exception e) {
            throw new RuntimeException("Auth API failed to reset password of member " + member.getUsername());
        }
        memberRepository.save(member);
    }
}