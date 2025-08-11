package org.example.schedulemanagementapp.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 유저 로그인 요청 DTO
@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
