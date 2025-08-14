package org.example.schedulemanagementapp.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.schedule.service.ScheduleService;
import org.example.schedulemanagementapp.domain.user.dto.UserBaseRequest;
import org.example.schedulemanagementapp.domain.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.domain.user.dto.UserLoginResponse;
import org.example.schedulemanagementapp.domain.user.dto.UserUpdateRequest;
import org.example.schedulemanagementapp.domain.user.entity.User;
import org.example.schedulemanagementapp.global.util.PasswordEncoder;
import org.example.schedulemanagementapp.global.exception.CustomException;
import org.example.schedulemanagementapp.global.exception.ErrorCode;
import org.example.schedulemanagementapp.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@link UserService}를 구현체
 *
 * <p>JPA를 사용하여 데이터를 데이터베이스에 저장, 조회, 수정, 삭제하는 기능 제공</p>
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ScheduleService scheduleService;

    @Transactional
    @Override
    public UserBaseResponse save(UserBaseRequest dto) {
        // 이메일 중복 검사
        Optional<User> us = userRepository.findByEmail(dto.getEmail());
        if (us.isPresent()) throw new CustomException(ErrorCode.DUPLICATE_EMAIL);

        // 비밀 번호 암호화
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        String encodingPassword = passwordEncoder.encode(dto.getPassword());

        // 유저 저장
        User user = new User(
                dto.getUsername(),
                dto.getEmail(),
                encodingPassword
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
        return userRepository.findById(userId).map(UserBaseResponse::of).orElse(null);
    }

    @Transactional
    @Override
    public UserBaseResponse updateUserById(Long sessionId, Long userId, UserUpdateRequest dto) {
        // 이메일 중복 검사
        Optional<User> us = userRepository.findByEmail(dto.getEmail());
        if (us.isPresent()) throw new CustomException(ErrorCode.DUPLICATE_EMAIL);

        User user = findUserByIdOrThrow(userId);

        // 유저 검증
        if (!Objects.equals(sessionId, user.getId()))
            throw new CustomException(ErrorCode.NO_PERMISSION);

        // 비밀 번호 암호화
        String password = user.getPassword();
        if (dto.getPassword() != null) {
            PasswordEncoder passwordEncoder = new PasswordEncoder();
            password = passwordEncoder.encode(dto.getPassword());
        }

        user.update(
                dto.getUsername() != null ? dto.getUsername() : user.getUsername(),
                dto.getEmail() != null ? dto.getEmail() : user.getEmail(),
                password
        );

        return UserBaseResponse.of(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long sessionId, Long userId) {
        User user = findUserByIdOrThrow(userId);

        // 유저 검증
        if (!Objects.equals(sessionId, user.getId()))
            throw new CustomException(ErrorCode.NO_PERMISSION);

        scheduleService.deleteAllScheduleByUser(user);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserLoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.AUTH_ERROR)
        );
        // 비밀 번호 검사
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new CustomException(ErrorCode.AUTH_ERROR);

        return UserLoginResponse.of(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByIdOrThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

}
