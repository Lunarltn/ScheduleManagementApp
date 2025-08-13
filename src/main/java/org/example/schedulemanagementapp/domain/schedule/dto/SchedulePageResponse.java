package org.example.schedulemanagementapp.domain.schedule.dto;

import lombok.Getter;
import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;

// 일정 부분 응답 DTO
@Getter
public class SchedulePageResponse {
    private final String title;
    private final String content;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String username;

    private SchedulePageResponse(String title, String content, int commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String username) {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.username = username;
    }

    public static SchedulePageResponse of(String title, String content, int commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt, String username) {
        return new SchedulePageResponse(title, content, commentCount, createdAt, modifiedAt, username);
    }

    public static SchedulePageResponse of(Schedule schedule, int commentCount) {

        return new SchedulePageResponse(
                schedule.getTitle(),
                schedule.getContent(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getUser().getUsername()
        );
    }
}
