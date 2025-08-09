package org.example.schedulemanagementapp.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulemanagementapp.schedule.entity.Schedule;

import java.time.LocalDateTime;

// 일정 부분 응답 DTO
@Getter
public class ScheduleGetOneResponse {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private ScheduleGetOneResponse(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ScheduleGetOneResponse of(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new ScheduleGetOneResponse(id, userId, title, content, createdAt, modifiedAt);
    }

    public static ScheduleGetOneResponse of(Schedule schedule) {

        return new ScheduleGetOneResponse(
                schedule.getId(),
                schedule.getUserId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
