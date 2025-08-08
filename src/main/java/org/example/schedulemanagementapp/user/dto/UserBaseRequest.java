package org.example.schedulemanagementapp.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserBaseRequest {
    private String username;
    private String email;
    private String password;
}
