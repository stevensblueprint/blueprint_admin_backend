package com.sitblueprint.admin.dtos.member;

import com.sitblueprint.admin.validator.EmailDomain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {
  @NonNull
  private String username;

  @NonNull
  @Size(min = 8)
  private String password;

  @NonNull
  @Email
  @EmailDomain
  private String email;
}
