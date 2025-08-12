package org.example.schedulemanagementapp.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

// 유저 로그인 요청 DTO
@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    @Length(max = 30, message = "이메일은 30글자 이하로 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀 번호를 입력해주세요.")
    @Length(max = 15, message = "비밀 번호는 15글자 이하로 입력해주세요.")
    private String password;
}
