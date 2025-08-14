package org.example.schedulemanagementapp.domain.user.dto;

import lombok.Getter;
import org.example.schedulemanagementapp.domain.user.entity.User;

import java.time.LocalDateTime;

// 유저 기본 응답 DTO
@Getter
public class UserBaseResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private UserBaseResponse(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserBaseResponse of(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new UserBaseResponse(id, username, email, createdAt, modifiedAt);
    }

    public static UserBaseResponse of(User user) {
        return new UserBaseResponse(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }
}
