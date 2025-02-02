package com.sitblueprint.admin.service;

import com.sitblueprint.admin.dtos.member.MemberDTO;
import com.sitblueprint.admin.dtos.member.RegistrationRequestDTO;
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.model.Token;
import com.sitblueprint.admin.repository.MemberRepository;
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

	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public List<MemberDTO> getAllMembers() {
		return memberRepository.findAll().stream().map(Member::toDTO).collect(Collectors.toList());
	}

	@Override
	public MemberDTO getMemberById(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new NoSuchElementException("Member with id " + memberId + " was not found"));
		return member.toDTO();
	}

	@Override
	public MemberDTO createMember(MemberDTO member) {
		Member savedMember = memberRepository.save(member.toEntity());
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
}