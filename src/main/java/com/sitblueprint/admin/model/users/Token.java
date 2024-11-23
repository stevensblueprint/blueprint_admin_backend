package com.sitblueprint.admin.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Token {
  @Id
  @SequenceGenerator(
      name = "sequence_token",
      sequenceName = "token_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "token_sequence"
  )
  private Long id;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime expiresAt;

  private LocalDateTime confirmedAt;

  @ManyToOne
  @JoinColumn(
      nullable = false,
      name = "member_id"
  )
  private Member member;

  public static Token of(String token, LocalDateTime createdAt, LocalDateTime expiresAt,
      Member member) {
    return Token.builder()
        .token(token)
        .createdAt(createdAt)
        .expiresAt(expiresAt)
        .member(member)
        .build();
  }
}
