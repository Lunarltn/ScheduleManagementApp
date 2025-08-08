package org.example.schedulemanagementapp.schedule.service;

import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseRequest;
import org.example.schedulemanagementapp.schedule.dto.ScheduleBaseResponse;
import org.example.schedulemanagementapp.schedule.dto.SchedulerUpdateRequest;

import java.util.List;

public interface ScheduleService {
    ScheduleBaseResponse save(ScheduleBaseRequest request);

    List<ScheduleBaseResponse> findAll();

    ScheduleBaseResponse findScheduleById(Long scheduleId);

    ScheduleBaseResponse updateScheduleById(Long scheduleId, SchedulerUpdateRequest request);

    void deleteScheduleById(Long scheduleId);
}
