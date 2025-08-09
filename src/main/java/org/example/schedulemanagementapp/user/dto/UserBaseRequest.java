package org.example.schedulemanagementapp.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 유저 기본 요청 DTO
@Getter
@NoArgsConstructor
public class UserBaseRequest {
    private String username;
    private String email;
    private String password;
}
