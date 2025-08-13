package org.example.schedulemanagementapp.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.comment.entity.Comment;
import org.example.schedulemanagementapp.domain.comment.repository.CommentRepository;
import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.domain.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.domain.schedule.dto.SchedulePageResponse;
import org.example.schedulemanagementapp.domain.schedule.dto.SchedulerUpdateRequest;
import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;
import org.example.schedulemanagementapp.domain.schedule.repository.ScheduleRepository;
import org.example.schedulemanagementapp.domain.user.entity.User;
import org.example.schedulemanagementapp.domain.user.repository.UserRepository;
import org.example.schedulemanagementapp.domain.user.service.UserService;
import org.example.schedulemanagementapp.global.exception.CustomException;
import org.example.schedulemanagementapp.global.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@link ScheduleService}의 구현체
 *
 * <p>JPA를 사용하여 데이터를 데이터베이스에 저장, 조회, 수정, 삭제하는 기능 제공</p>
 */
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public ScheduleBaseResponse save(Long userId, ScheduleBaseRequest requestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Schedule schedule = new Schedule(
                user,
                requestDto.getTitle(),
                requestDto.getContent()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleBaseResponse.of(savedSchedule);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SchedulePageResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Schedule> schedulePage = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);

        return schedulePage.map(schedule -> {
            int commentCount = commentRepository.countBySchedule(schedule);
            return SchedulePageResponse.of(schedule, commentCount);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleBaseResponse findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).map(ScheduleBaseResponse::of).orElse(null);
    }

    @Transactional
    @Override
    public ScheduleBaseResponse updateScheduleById(Long userId, Long scheduleId, SchedulerUpdateRequest requestDto) {
        Schedule schedule = findScheduleByIdOrThrow(scheduleId);
        // 유저 검증
        if (!CheckUserIdInSchedule(schedule, userId))
            throw new CustomException(ErrorCode.NO_PERMISSION);

        schedule.update(
                Optional.ofNullable(requestDto.getTitle()).orElse(schedule.getTitle()),
                Optional.ofNullable(requestDto.getContent()).orElse(schedule.getContent())
        );

        return ScheduleBaseResponse.of(schedule);
    }

    @Transactional
    @Override
    public void deleteScheduleById(Long userId, Long scheduleId) {
        Schedule schedule = findScheduleByIdOrThrow(scheduleId);
        // 유저 검증
        if (!CheckUserIdInSchedule(schedule, userId))
            throw new CustomException(ErrorCode.NO_PERMISSION);

        scheduleRepository.delete(schedule);
    }

    @Transactional(readOnly = true)
    @Override
    public Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND)
        );
    }

    @Transactional
    @Override
    public void deleteAllScheduleByUser(User user) {

        List<Schedule> schedules = scheduleRepository.findAllByUser(user);
        schedules.forEach(this::deleteScheduleByScheduleId);
    }

    @Transactional
    public void deleteScheduleByScheduleId(Schedule schedule) {
        commentRepository.deleteAllBySchedule(schedule);
        scheduleRepository.delete(schedule);
    }

    /**
     * 일정 작성자 아이디를 확인하고 비교값 반환
     *
     * @param schedule 일정 엔티티
     * @param userId   유저 아이디
     */
    private boolean CheckUserIdInSchedule(Schedule schedule, Long userId) {
        return Objects.equals(schedule.getUser().getId(), userId);
    }

}
