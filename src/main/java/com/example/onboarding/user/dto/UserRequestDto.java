package com.example.onboarding.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
  private String nickname;
  private String email;
  private String password;
  private boolean isAdmin;
}
