package org.example.schedulemanagementapp.schedule.repository;

import org.example.schedulemanagementapp.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

// 일정 레포지토리
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
