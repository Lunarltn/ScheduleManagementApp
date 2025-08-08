package org.example.schedulemanagementapp.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulemanagementapp.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SchedulerUpdateResponse {
    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static SchedulerUpdateResponse of(Long id, String username, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new SchedulerUpdateResponse(id, username, title, content, createdAt, modifiedAt);
    }

    public static SchedulerUpdateResponse of(Schedule schedule) {

        return new SchedulerUpdateResponse(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
