package org.example.schedulemanagementapp.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.user.dto.UserBaseRequest;
import org.example.schedulemanagementapp.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.user.service.UserService;
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
     * @param request 유저 기본 요청 DTO
     * @return HTTP 상태 코드와 유저 기본 응답 DTO
     */
    @PostMapping
    public ResponseEntity<UserBaseResponse> save(@RequestBody UserBaseRequest request) {
        return new ResponseEntity<>(userService.save(request), HttpStatus.CREATED);
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
     * @param userId  유저 아이디
     * @param request 유저 수정 요청 DTO
     * @return HTTP 상태 코드와 유저 기본 응답 DTO
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<UserBaseResponse> updateScheduleById(@PathVariable Long userId, @RequestBody UserBaseRequest request) {
        return ResponseEntity.ok(userService.updateUserById(userId, request));
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
