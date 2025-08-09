package org.example.schedulemanagementapp.schedule.service;

import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.schedule.dto.SchedulerUpdateRequest;

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
     * @param request 일정 기본 요청 DTO
     * @return 일정 기본 응답 DTO
     */
    ScheduleBaseResponse save(ScheduleBaseRequest request);

    /**
     * 전체 일정 조회
     *
     * @return 일정 기본 요청 DTO의 List
     */
    List<ScheduleBaseResponse> findAll();

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
     * @param scheduleId 일정 아이디
     * @param request    일정 수정 요청 DTO
     * @return 일정 기본 요청 DTO
     */
    ScheduleBaseResponse updateScheduleById(Long scheduleId, SchedulerUpdateRequest request);

    /**
     * 일정 삭제
     *
     * @param scheduleId 일정 아이디
     */
    void deleteScheduleById(Long scheduleId);
}
