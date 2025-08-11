package org.example.schedulemanagementapp.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 유저 수정 요청
@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String email;
    private String password;
}
