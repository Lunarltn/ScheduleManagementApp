package org.example.schedulemanagementapp.domain.schedule.repository;

import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

// 일정 레포지토리
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findScheduleById(Long id);
}
