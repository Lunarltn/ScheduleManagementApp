package org.example.schedulemanagementapp.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 일정 기본 요청 DTO
@Getter
@NoArgsConstructor
public class ScheduleBaseRequest {
    private Long userId;
    private String title;
    private String content;
}
