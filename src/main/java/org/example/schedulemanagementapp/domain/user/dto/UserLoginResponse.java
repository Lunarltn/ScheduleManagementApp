package org.example.schedulemanagementapp.domain.user.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {
    private final Long id;
    private final String username;
    private final String email;

    private UserLoginResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static UserLoginResponse of(Long id, String username, String email) {
        return new UserLoginResponse(id, username, email);
    }
}
