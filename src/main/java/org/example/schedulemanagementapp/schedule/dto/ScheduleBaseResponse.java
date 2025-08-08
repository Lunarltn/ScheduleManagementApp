package org.example.schedulemanagementapp.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulemanagementapp.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleBaseResponse {
    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static ScheduleBaseResponse of(Long id, String username, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ScheduleBaseResponse(id, username, title, content, createdAt, modifiedAt);
    }

    public static ScheduleBaseResponse of(Schedule schedule) {

        return new ScheduleBaseResponse(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
