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

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    @Override
    public ScheduleBaseResponse save(ScheduleBaseRequest request) {
        Schedule schedule = new Schedule(
                request.getUsername(),
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
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule Not Found!")
        );

        schedule.update(
                request.getUsername() != null ? request.getUsername() : schedule.getUsername(),
                request.getTitle() != null ? request.getTitle() : schedule.getTitle(),
                request.getContent() != null ? request.getContent() : schedule.getContent()
        );

        return ScheduleBaseResponse.of(schedule);
    }

    @Transactional
    @Override
    public void deleteScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("Schedule Not Found!")
        );
        scheduleRepository.delete(schedule);
    }


}
