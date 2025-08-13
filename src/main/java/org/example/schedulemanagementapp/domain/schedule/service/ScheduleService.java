package org.example.schedulemanagementapp.domain.schedule.service;

import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.domain.schedule.dto.SchedulePageResponse;
import org.example.schedulemanagementapp.domain.schedule.dto.SchedulerUpdateRequest;
import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;
import org.example.schedulemanagementapp.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 일정 관련 로직을 처리하는 서비스 인터페이스
 *
 * <p>일정 저장, 전체 조회, 부분 조회, 수정, 삭제 기능 정의</p>
 */
public interface ScheduleService {
    /**
     * 새로운 일정 저장
     *
     * @param userId     유저 아이디
     * @param requestDto 일정 기본 요청 DTO
     * @return 일정 기본 응답 DTO
     */
    ScheduleBaseResponse save(Long userId, ScheduleBaseRequest requestDto);

    /**
     * 전체 일정 조회
     *
     * @param page 현재 페이지
     * @param size 페이지 크기
     * @return 일정 기본 응답 DTO의 Page
     */
    Page<SchedulePageResponse> findAll(int page, int size);

    /**
     * 아이디 일정 조회
     *
     * @param scheduleId 일정 아이디
     * @return 일정 기본 응답 DTO
     */
    ScheduleBaseResponse findScheduleById(Long scheduleId);

    /**
     * 일정 수정
     *
     * @param userId     유저 아이디
     * @param scheduleId 일정 아이디
     * @param requestDto 일정 수정 요청 DTO
     * @return 일정 기본 응답 DTO
     */
    ScheduleBaseResponse updateScheduleById(Long userId, Long scheduleId, SchedulerUpdateRequest requestDto);

    /**
     * 일정 삭제
     *
     * @param userId     유저 아이디
     * @param scheduleId 일정 아이디
     */
    void deleteScheduleById(Long userId, Long scheduleId);

    /**
     * 레포지토리에서 아이디로 일정 엔티티를 찾아 반환하고 없다면 예외 발생
     *
     * @param scheduleId 일정 아이디
     * @return 일정 엔티티
     */
    Schedule findScheduleByIdOrThrow(Long scheduleId);

    void deleteAllScheduleByUser(User user);
}
