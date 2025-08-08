package org.example.schedulemanagementapp.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.user.dto.UserBaseRequest;
import org.example.schedulemanagementapp.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserBaseResponse> save(@RequestBody UserBaseRequest request) {
        return new ResponseEntity<>(userService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserBaseResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserBaseResponse> findScheduleById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserBaseResponse> updateScheduleById(@PathVariable Long userId, @RequestBody UserBaseRequest request) {
        return ResponseEntity.ok(userService.updateUserById(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
