package org.example.schedulemanagementapp.user.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.user.dto.UserBaseRequest;
import org.example.schedulemanagementapp.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.user.entity.User;
import org.example.schedulemanagementapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserBaseResponse save(UserBaseRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail()
        );

        User savedUser = userRepository.save(user);

        return UserBaseResponse.of(savedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserBaseResponse> findAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserBaseResponse::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserBaseResponse findUserById(Long userId) {
        return userRepository.findById(userId).map(UserBaseResponse::of).orElseThrow(
                () -> new IllegalArgumentException("User Not Found!")
        );
    }

    @Transactional
    @Override
    public UserBaseResponse updateUserById(Long userId, UserBaseRequest request) {
        User user = findUserByIdOrThrow(userId);

        user.update(
                request.getUsername() != null ? request.getUsername() : user.getUsername(),
                request.getEmail() != null ? request.getEmail() : user.getEmail()
        );

        return UserBaseResponse.of(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        User user = findUserByIdOrThrow(userId);
        userRepository.delete(user);
    }

    private User findUserByIdOrThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User Not Found!"));
    }

}
