package org.example.schedulemanagementapp.domain.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.domain.schedule.dto.SchedulerUpdateRequest;
import org.example.schedulemanagementapp.domain.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 관련 요청을 처리하는 컨트롤러
 *
 * <p>요청을 받아 일정 저장, 전체 조회, 부분 조회, 수정, 삭제 기능 제공</p>
 */
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 새로운 일정 저장
     *
     * @param dto 일정 기본 요청 DTO
     * @return HTTP 상태 코드와 일정 기본 응답 DTO
     */
    @PostMapping
    public ResponseEntity<ScheduleBaseResponse> save(@RequestBody ScheduleBaseRequest dto) {
        return new ResponseEntity<>(scheduleService.save(dto), HttpStatus.CREATED);
    }

    /**
     * 전체 일정 조회
     *
     * @return HTTP 상태 코드와 일정 기본 요청 DTO의 List
     */
    @GetMapping
    public ResponseEntity<List<ScheduleBaseResponse>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    /**
     * 아이디 일정 조회
     *
     * @param scheduleId 일정 아이디
     * @return HTTP 상태 코드와 일정 기본 응답 DTO
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleBaseResponse> findScheduleById(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findScheduleById(scheduleId));
    }

    /**
     * 일정 수정
     *
     * @param scheduleId 일정 아이디
     * @param dto        일정 수정 요청 DTO
     * @return HTTP 상태 코드와 일정 기본 응답 DTO
     */
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleBaseResponse> updateScheduleById(@PathVariable Long scheduleId, @RequestBody SchedulerUpdateRequest dto) {
        return ResponseEntity.ok(scheduleService.updateScheduleById(scheduleId, dto));
    }

    /**
     * 일정 삭제
     *
     * @param scheduleId 일정 아이디
     * @return HTTP 상태 코드
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long scheduleId) {
        scheduleService.deleteScheduleById(scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
