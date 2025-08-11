package org.example.schedulemanagementapp.domain.user.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class UserErrorResponse {
    private final int status;
    private final String errorCode;
    private final String errorMessage;
    private final String path;
    private final LocalDateTime timestamp;

    private UserErrorResponse(int status, String errorCode, String errorMessage, String path) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public static UserErrorResponse of(HttpStatus status, String errorCode, String errorMessage, String path) {
        return new UserErrorResponse(status.value(), errorCode, errorMessage, path);
    }
}
