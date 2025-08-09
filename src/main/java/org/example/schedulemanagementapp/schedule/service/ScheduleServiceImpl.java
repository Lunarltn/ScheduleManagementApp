package org.example.schedulemanagementapp.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.schedule.dto.SchedulerUpdateRequest;
import org.example.schedulemanagementapp.schedule.entity.Schedule;
import org.example.schedulemanagementapp.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    @Override
    public ScheduleBaseResponse save(ScheduleBaseRequest request) {
        Schedule schedule = new Schedule(
                request.getUserId(),
                request.getTitle(),
                request.getContent()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleBaseResponse.of(savedSchedule);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleBaseResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream().map(ScheduleBaseResponse::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleBaseResponse findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).map(ScheduleBaseResponse::of).orElseThrow(
                () -> new IllegalArgumentException("Schedule Not Found!")
        );
    }

    @Transactional
    @Override
    public ScheduleBaseResponse updateScheduleById(Long scheduleId, SchedulerUpdateRequest request) {
        Schedule schedule = findScheduleByIdOrThrow(scheduleId);

        schedule.update(
                request.getUserId() != null ? request.getUserId() : schedule.getUserId(),
                request.getTitle() != null ? request.getTitle() : schedule.getTitle(),
                request.getContent() != null ? request.getContent() : schedule.getContent()
        );

        return ScheduleBaseResponse.of(schedule);
    }

    @Transactional
    @Override
    public void deleteScheduleById(Long scheduleId) {
        Schedule schedule = findScheduleByIdOrThrow(scheduleId);
        scheduleRepository.delete(schedule);
    }

    /**
     * 레포지토리에서 아이디를 찾아 일정 엔티티를 반환하고 없다면 예외 발생
     *
     * @param scheduleId 일정 아이디
     * @return 일정 엔티티
     */
    private Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule Not Found!")
        );
    }
}
