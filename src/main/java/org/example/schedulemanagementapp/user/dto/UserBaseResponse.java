package org.example.schedulemanagementapp.user.dto;

import lombok.Getter;
import org.example.schedulemanagementapp.user.entity.User;

// 유저 기본 응답 DTO
@Getter
public class UserBaseResponse {
    private final Long id;
    private final String username;
    private final String email;

    private UserBaseResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static UserBaseResponse of(Long id, String username, String email) {
        return new UserBaseResponse(id, username, email);
    }

    public static UserBaseResponse of(User user) {
        return new UserBaseResponse(user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
