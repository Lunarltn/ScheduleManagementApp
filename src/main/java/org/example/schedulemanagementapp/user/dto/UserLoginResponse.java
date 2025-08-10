package org.example.schedulemanagementapp.user.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {
    private final Long id;
    private final String username;

    private UserLoginResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static UserLoginResponse of(Long id, String username) {
        return new UserLoginResponse(id, username);
    }
}
