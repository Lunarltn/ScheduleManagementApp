package org.example.schedulemanagementapp.domain.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.domain.schedule.dto.SchedulePageResponse;
import org.example.schedulemanagementapp.domain.schedule.dto.SchedulerUpdateRequest;
import org.example.schedulemanagementapp.domain.schedule.service.ScheduleService;
import org.example.schedulemanagementapp.domain.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.global.constant.Const;
import org.example.schedulemanagementapp.global.util.SessionUtils;
import org.springframework.data.domain.Page;
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
     * @param requestDto 일정 기본 요청 DTO
     * @param request    HTTP 요청 정보
     * @return HTTP 상태 코드와 일정 기본 응답 DTO
     */
    @PostMapping
    public ResponseEntity<ScheduleBaseResponse> save(@Valid @RequestBody ScheduleBaseRequest requestDto,
                                                     HttpServletRequest request
    ) {
        // 로그인 유저 아이디 확인
        Long loginUserId = SessionUtils.GetLoginUserIdBySelvet(request);

        return new ResponseEntity<>(scheduleService.save(loginUserId, requestDto), HttpStatus.CREATED);
    }

    /**
     * 전체 일정 페이징 조회
     *
     * @param page 현제 페이지
     * @param size 페이지 크기
     * @return HTTP 상태 코드와 일정 기본 요청 DTO의 List
     */
    @GetMapping
    public ResponseEntity<List<SchedulePageResponse>> findAll(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<SchedulePageResponse> schedulePage = scheduleService.findAll(page, size);

        return ResponseEntity.ok(schedulePage.getContent());
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
     * @param requestDto 일정 수정 요청 DTO
     * @param request    HTTP 요청 정보
     * @return HTTP 상태 코드와 일정 기본 응답 DTO
     */
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleBaseResponse> updateScheduleById(@PathVariable Long scheduleId,
                                                                   @Valid @RequestBody SchedulerUpdateRequest requestDto,
                                                                   HttpServletRequest request) {
        // 로그인 유저 아이디 확인
        Long loginUserId = SessionUtils.GetLoginUserIdBySelvet(request);

        return ResponseEntity.ok(scheduleService.updateScheduleById(loginUserId, scheduleId, requestDto));
    }

    /**
     * 일정 삭제
     *
     * @param scheduleId 일정 아이디
     * @param request    HTTP 요청 정보
     * @return HTTP 상태 코드
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long scheduleId, HttpServletRequest request) {
        // 로그인 유저 아이디 확인
        Long loginUserId = SessionUtils.GetLoginUserIdBySelvet(request);

        scheduleService.deleteScheduleById(loginUserId, scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
