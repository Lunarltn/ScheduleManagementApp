package org.example.schedulemanagementapp.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 유저 로그인 요청 DTO
@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
