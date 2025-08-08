package org.example.schedulemanagementapp.user.service;

import org.example.schedulemanagementapp.user.dto.UserBaseRequest;
import org.example.schedulemanagementapp.user.dto.UserBaseResponse;

import java.util.List;

public interface UserService {
    UserBaseResponse save(UserBaseRequest request);

    List<UserBaseResponse> findAll();

    UserBaseResponse findUserById(Long userId);

    UserBaseResponse updateUserById(Long userId, UserBaseRequest request);

    void deleteUserById(Long userId);
}
