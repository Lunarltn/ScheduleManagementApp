package org.example.schedulemanagementapp.schedule.repository;

import org.example.schedulemanagementapp.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
