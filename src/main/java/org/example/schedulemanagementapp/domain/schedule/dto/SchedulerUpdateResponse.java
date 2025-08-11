package org.example.schedulemanagementapp.domain.schedule.dto;

import lombok.Getter;
import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;

// 일정 수정 응답 DTO
@Getter
public class SchedulerUpdateResponse {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private SchedulerUpdateResponse(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static SchedulerUpdateResponse of(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new SchedulerUpdateResponse(id, userId, title, content, createdAt, modifiedAt);
    }

    public static SchedulerUpdateResponse of(Schedule schedule) {
        return new SchedulerUpdateResponse(
                schedule.getId(),
                schedule.getUserId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
