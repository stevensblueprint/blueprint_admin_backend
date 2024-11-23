package com.sitblueprint.admin.service.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sitblueprint.admin.dtos.member.MemberDTO;
import com.sitblueprint.admin.dtos.member.RegistrationRequestDTO;
import com.sitblueprint.admin.model.users.Member;
import com.sitblueprint.admin.model.users.Token;
import com.sitblueprint.admin.repository.users.MemberRepository;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
	@Mock
	private MemberRepository memberRepository;

	@Mock
	private TokenService tokenService;

	private MemberServiceImpl memberService;
	private Member testMember;
	private MemberDTO testMemberDTO;

	@BeforeEach
	void setUp() {
		memberService = new MemberServiceImpl(memberRepository, tokenService);
		testMember = Member.builder().id(1L).username("testUsername").name("Test Name").password("Test Password")
				.isActive(true).build();

		testMemberDTO = testMember.toDTO();
	}

	@Test
	void getMember_ShouldReturnListOfMembers() {
		// Arrange
		List<Member> members = Collections.singletonList(testMember);
		when(memberRepository.findAll()).thenReturn(members);

		// Act
		List<MemberDTO> result = memberService.getAllMembers();

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(testMemberDTO.getUsername(), result.get(0).getUsername());
		verify(memberRepository).findAll();
	}

	@Test
	void getMemberById_WhenMemberExists_ShouldReturnMember() {
		// Arrange
		when(memberRepository.findById(1L)).thenReturn(Optional.of(testMember));

		// Act
		MemberDTO result = memberService.getMemberById(1L);

		// Assert
		assertNotNull(result);
		assertEquals(testMemberDTO.getId(), result.getId());
		assertEquals(testMemberDTO.getUsername(), result.getUsername());
		verify(memberRepository).findById(1L);
	}

	@Test
	void getMemberById_WhenMemberDoesNotExist_ShouldThrowException() {
		// Arrange
		when(memberRepository.findById(1L)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(NoSuchElementException.class, () -> memberService.getMemberById(1L));
		verify(memberRepository).findById(1L);
	}

	@Test
	void createMember_ShouldSaveAndReturnMember() {
		// Arrange
		when(memberRepository.save(any(Member.class))).thenReturn(testMember);

		// Act
		MemberDTO result = memberService.createMember(testMemberDTO);

		// Assert
		assertNotNull(result);
		assertEquals(testMemberDTO.getUsername(), result.getUsername());
		verify(memberRepository).save(any(Member.class));
	}

	@Test
	void updateMember_WhenMemberExists_ShouldUpdateAndReturnMember() {
		// Arrange
		when(memberRepository.existsById(1L)).thenReturn(true);
		when(memberRepository.findById(1L)).thenReturn(Optional.of(testMember));
		when(memberRepository.save(any(Member.class))).thenReturn(testMember);

		// Act
		MemberDTO result = memberService.updateMember(testMemberDTO);

		// Assert
		assertNotNull(result);
		assertEquals(testMemberDTO.getUsername(), result.getUsername());
		verify(memberRepository).save(any(Member.class));
	}

	@Test
	void updateMember_WhenMemberDoesNotExist_ShouldThrowException() {
		// Arrange
		when(memberRepository.existsById(1L)).thenReturn(false);

		// Act & Assert
		assertThrows(RuntimeException.class, () -> memberService.updateMember(testMemberDTO));
	}

	@Test
	void deleteMemberById_WhenMemberExists_ShouldDelete() {
		// Arrange
		when(memberRepository.findById(1L)).thenReturn(Optional.of(testMember));

		// Act
		memberService.deleteMemberById(1L);

		// Assert
		verify(memberRepository).deleteById(1L);
	}

	@Test
	void deleteMemberById_WhenMemberDoesNotExist_ShouldThrowException() {
		// Arrange
		when(memberRepository.findById(1L)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(RuntimeException.class, () -> memberService.deleteMemberById(1L));
	}

	@Test
	void enableMemberById_WhenMemberExists_ShouldEnableMember() {
		// Arrange
		when(memberRepository.findById(1L)).thenReturn(Optional.of(testMember));
		when(memberRepository.save(any(Member.class))).thenReturn(testMember);

		// Act
		memberService.enableMemberById(1L);

		// Assert
		verify(memberRepository).save(any(Member.class));
		assertTrue(testMember.isActive());
	}

	@Test
	void disableMemberById_WhenMemberExists_ShouldDisableMember() {
		// Arrange
		when(memberRepository.findById(1L)).thenReturn(Optional.of(testMember));
		when(memberRepository.save(any(Member.class))).thenReturn(testMember);

		// Act
		memberService.disableMemberById(1L);

		// Assert
		verify(memberRepository).save(any(Member.class));
		assertFalse(testMember.isActive());
	}

	@Test
	void resetPassword_WhenMemberExists_ShouldUpdatePassword() {
		// Arrange
		when(memberRepository.findById(1L)).thenReturn(Optional.of(testMember));
		when(memberRepository.save(any(Member.class))).thenReturn(testMember);
		String newPassword = "newPassword123";

		// Act
		memberService.resetPassword(1L, newPassword);

		// Assert
		verify(memberRepository).save(any(Member.class));
		assertEquals(newPassword, testMember.getPassword());
	}

	@Test
	void signUpMember_WhenUsernameNotTaken_ShouldCreateMemberAndReturnToken() {
		// Arrange
		RegistrationRequestDTO request = new RegistrationRequestDTO();
		request.setUsername("newUser");
		request.setName("New User");
		request.setEmail("new@example.com");
		request.setPassword("password123");

		when(memberRepository.findByUsername("newUser")).thenReturn(null);
		when(memberRepository.save(any(Member.class))).thenReturn(testMember);
		doNothing().when(tokenService).saveConfirmationToken(any(Token.class));

		// Act
		String token = memberService.signUpMember(request);

		// Assert
		assertNotNull(token);
		verify(memberRepository).save(any(Member.class));
		verify(tokenService).saveConfirmationToken(any(Token.class));
	}

	@Test
	void signUpMember_WhenUsernameExists_ShouldThrowException() {
		// Arrange
		RegistrationRequestDTO request = new RegistrationRequestDTO();
		request.setUsername("existingUser");
		request.setName("Existing User");
		request.setEmail("existing@example.com");
		request.setPassword("password123");

		when(memberRepository.findByUsername("existingUser")).thenReturn(testMember);

		// Act & Assert
		assertThrows(IllegalStateException.class, () -> memberService.signUpMember(request));
	}
}
