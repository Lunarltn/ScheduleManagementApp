package org.example.schedulemanagementapp.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 유저 기본 요청 DTO
@Getter
@NoArgsConstructor
public class UserBaseRequest {
    @NotBlank(message = "유저 이름은 필수입니다.")
    private String username;
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email;
    @NotBlank(message = "비밀 번호 입력은 필수입니다.")
    private String password;
}
