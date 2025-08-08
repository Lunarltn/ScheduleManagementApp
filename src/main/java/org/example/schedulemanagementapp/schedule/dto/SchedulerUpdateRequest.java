package org.example.schedulemanagementapp.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SchedulerUpdateRequest {
    private Long userId;
    private String title;
    private String content;
}
