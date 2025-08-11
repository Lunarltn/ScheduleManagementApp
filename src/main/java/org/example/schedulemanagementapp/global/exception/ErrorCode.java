package org.example.schedulemanagementapp.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "VAL-001", "입력값이 유효하지 않습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "USR-001", "이미 가입된 이메일입니다."),
    AUTH_ERROR(HttpStatus.UNAUTHORIZED, "USR-002", "아이디 또는 비밀번호가 잘못되었습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USR-003", "유저를 찾을 수 없습니다."),
    NEED_AUTH(HttpStatus.UNAUTHORIZED, "USR-004", "로그인이 필요한 서비스 입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
