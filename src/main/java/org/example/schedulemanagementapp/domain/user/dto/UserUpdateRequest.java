package org.example.schedulemanagementapp.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

// 유저 수정 요청
@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    @Length(min = 1, max = 4, message = "유저 이름은 4글자 이하로 입력해주세요.")
    private String username;
    @Length(min = 1, max = 30, message = "이메일은 30글자 이하로 입력해주세요.")
    private String email;
    @Length(min = 1, max = 15, message = "비밀 번호는 15글자 이하로 입력해주세요.")
    private String password;
}
