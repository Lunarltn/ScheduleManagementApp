package org.example.schedulemanagementapp.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.user.dto.*;
import org.example.schedulemanagementapp.global.constant.Const;
import org.example.schedulemanagementapp.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유저 관련 요청을 처리하는 컨트롤러
 *
 * <p>요청을 받아 유저 저장, 전체 조회, 부분 조회, 수정, 삭제 기능 제공</p>
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 새로운 유저 저장
     *
     * @param requestDto 유저 기본 요청 DTO
     * @return HTTP 상태 코드와 유저 기본 응답 DTO
     */
    @PostMapping("/signup")
    public ResponseEntity<UserBaseResponse> save(@Valid @RequestBody UserBaseRequest requestDto) {
        return new ResponseEntity<>(userService.save(requestDto), HttpStatus.CREATED);
    }

    /**
     * 사용자 로그인 처리
     *
     * @param requestDto 유저 로그인 요청 DTO
     * @param request    HTTP 인증 정보
     * @return HTTP 상태 코드와 유저 로그인 응답 DTO
     */
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest requestDto, HttpServletRequest request) {
        UserLoginResponse loginResponseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());
        Long userId = loginResponseDto.getId();

        HttpSession session = request.getSession();
        UserBaseResponse loginUser = userService.findUserById(userId);
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return ResponseEntity.ok(loginResponseDto);
    }

    /**
     * 사용자 로그아웃 처리
     *
     * @param request HTTP 인증 정보
     * @return 상태 코드
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 전체 유저 조회
     *
     * @return HTTP 상태 코드와 유저 기본 요청 DTO의 List
     */
    @GetMapping
    public ResponseEntity<List<UserBaseResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * 아이디 유저 조회
     *
     * @param userId 유저 아이디
     * @return HTTP 상태 코드와 유저 기본 응답 DTO
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserBaseResponse> findScheduleById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    /**
     * 유저 수정
     *
     * @param userId     유저 아이디
     * @param requestDto 유저 수정 요청 DTO
     * @return HTTP 상태 코드와 유저 기본 응답 DTO
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<UserBaseResponse> updateScheduleById(@PathVariable Long userId, @RequestBody UserUpdateRequest requestDto) {
        return ResponseEntity.ok(userService.updateUserById(userId, requestDto));
    }

    /**
     * 유저 삭제
     *
     * @param userId 유저 아이디
     * @return HTTP 상태 코드
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
