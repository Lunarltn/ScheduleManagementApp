package org.example.schedulemanagementapp.user.service;

import org.example.schedulemanagementapp.user.dto.UserBaseRequest;
import org.example.schedulemanagementapp.user.dto.UserBaseResponse;

import java.util.List;

/**
 * 유저 관련 로직을 처리하는 서비스 인터페이스
 *
 * <p>유저 저장, 전체 조회, 부분 조회, 수정, 삭제 기능 정의</p>
 */
public interface UserService {
    /**
     * 새로운 유저 저장
     *
     * @param request 유저 기본 요청 DTO
     * @return 유저 기본 응답 DTO
     */
    UserBaseResponse save(UserBaseRequest request);

    /**
     * 전체 유저 조회
     *
     * @return 유저 기본 응답 DTO의 List
     */
    List<UserBaseResponse> findAll();

    /**
     * 부분 유저 조회
     *
     * @param userId 유저 아이디
     * @return 유저 기본 응답 DTO
     */
    UserBaseResponse findUserById(Long userId);

    /**
     * 유저 수정
     *
     * @param userId  유저 아이디
     * @param request 유저 기본 요청 DTO
     * @return 유저 기본 응답 DTO
     */
    UserBaseResponse updateUserById(Long userId, UserBaseRequest request);

    /**
     * 유저 삭제
     *
     * @param userId 유저 아이디
     */
    void deleteUserById(Long userId);
}
