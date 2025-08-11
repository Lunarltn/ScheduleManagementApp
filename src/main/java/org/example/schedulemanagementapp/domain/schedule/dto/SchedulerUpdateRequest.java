package org.example.schedulemanagementapp.domain.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 일정 수정 요청 DTO
@Getter
@NoArgsConstructor
public class SchedulerUpdateRequest {
    private Long userId;
    private String title;
    private String content;
}
