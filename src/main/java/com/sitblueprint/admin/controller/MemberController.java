package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.dtos.member.MemberDTO;
import com.sitblueprint.admin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

	MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping
	public List<MemberDTO> getAllMembers() {
		return memberService.getAllMembers();
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMember(@PathVariable("memberId") Long memberId) {
		try {
			MemberDTO member = memberService.getMemberById(memberId);
			return ResponseEntity.ok(member);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("Invalid member id format");
		}
	}

	@PostMapping
	public MemberDTO createMember(@RequestBody MemberDTO member) {
		return memberService.createMember(member);
	}

	@PutMapping
	public MemberDTO updateMember(@RequestBody MemberDTO member) {
		return memberService.updateMember(member);
	}

	@DeleteMapping
	public void deleteMember(String memberId) {
		memberService.deleteMemberById(Long.parseLong(memberId));
	}

	@PostMapping("enable/{memberId}")
	public void enableMember(@PathVariable("memberId") String memberId) {
		memberService.enableMemberById(Long.parseLong(memberId));
	}

	@PostMapping("disable/{memberId}")
	public void disableMember(@PathVariable("memberId") String memberId) {
		memberService.disableMemberById(Long.parseLong(memberId));
	}

	@PutMapping("reset_password")
	public void resetPassword(@RequestBody String memberId, @RequestBody String newPassword) {
		memberService.resetPassword(Long.parseLong(memberId), newPassword);
	}
}
