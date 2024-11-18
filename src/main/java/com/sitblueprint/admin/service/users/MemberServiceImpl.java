package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.dtos.member.MemberDTO;
import com.sitblueprint.admin.dtos.member.RegistrationRequestDTO;
import com.sitblueprint.admin.model.users.Member;
import com.sitblueprint.admin.model.users.Token;
import com.sitblueprint.admin.repository.users.MemberRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, TokenService tokenService) {
        this.memberRepository = memberRepository;
        this.tokenService = tokenService;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
            .stream()
            .map(Member::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("Member with id " + memberId + " was not found")
        );
        return member.toDTO();
    }

    @Override
    public MemberDTO createMember(MemberDTO member) {
        Member savedMember =  memberRepository.save(member.toEntity());
        return savedMember.toDTO();
    }

    @Override
    public MemberDTO updateMember(MemberDTO member) {
        Long memberId = member.getId();
        if (memberRepository.existsById(memberId)) {
            Optional<Member> existingMember = memberRepository.findById(memberId);
            if (existingMember.isPresent()) {
                Member newMember = member.toEntity();
                newMember.setId(memberId);
                return memberRepository.save(newMember).toDTO();
            }
        }
        throw new RuntimeException("Member with id " + memberId + " was not found");
    }

    @Transactional
    @Override
    public void deleteMemberById(Long memberId) {
        Optional<Member> optionalMemberToDelete = memberRepository.findById(memberId);
        if (optionalMemberToDelete.isEmpty()) {
            throw new RuntimeException("Member with id " + memberId + " was not found");
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
        memberRepository.save(member);
    }

    @Override
    public String signUpMember(RegistrationRequestDTO registrationRequestDTO) {
        Member memberExists = memberRepository.findByUsername(registrationRequestDTO.getUsername());
        if (memberExists != null) {
            throw new IllegalStateException(
                String.format("Member %s already exists", registrationRequestDTO.getUsername())
            );
        }
        String encodedPassword = registrationRequestDTO.getPassword();
        Member member = Member.builder()
            .username(registrationRequestDTO.getUsername())
            .name(registrationRequestDTO.getName())
            .password(encodedPassword)
            .email(registrationRequestDTO.getEmail())
            .isActive(true)
            .build();
        memberRepository.save(member);
        String token = UUID.randomUUID().toString();
        Token confirmationToken = Token.of(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            member
        );
        tokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
}