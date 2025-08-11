package org.example.schedulemanagementapp.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.user.dto.UserBaseRequest;
import org.example.schedulemanagementapp.domain.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.domain.user.dto.UserLoginResponse;
import org.example.schedulemanagementapp.domain.user.dto.UserUpdateRequest;
import org.example.schedulemanagementapp.domain.user.entity.User;
import org.example.schedulemanagementapp.global.exception.CustomException;
import org.example.schedulemanagementapp.global.exception.ErrorCode;
import org.example.schedulemanagementapp.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    @Override
    public UserBaseResponse save(UserBaseRequest dto) {
        // 이메일 중복 검사
        Optional<User> us = userRepository.findByEmail(dto.getEmail());
        if (us.isPresent()) throw new CustomException(ErrorCode.DUPLICATE_EMAIL);

        // 유저 저장
        User user = new User(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword()
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
    public UserBaseResponse updateUserById(Long userId, UserUpdateRequest dto) {
        User user = findUserByIdOrThrow(userId);

        user.update(
                dto.getUsername() != null ? dto.getUsername() : user.getUsername(),
                dto.getEmail() != null ? dto.getEmail() : user.getEmail(),
                dto.getPassword() != null ? dto.getPassword() : user.getPassword()
        );

        return UserBaseResponse.of(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        User user = findUserByIdOrThrow(userId);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserLoginResponse login(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password).orElseThrow(
                () -> new CustomException(ErrorCode.AUTH_ERROR)
        );

        return UserLoginResponse.of(user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * 레포지토리에서 아이디를 찾아 유저 엔티티를 반환하고 없다면 예외 발생
     *
     * @param userId 유저 아이디
     * @return 유저 엔티티
     */
    private User findUserByIdOrThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

}
