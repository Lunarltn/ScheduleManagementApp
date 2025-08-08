package org.example.schedulemanagementapp.schedule.dto;

import lombok.Getter;

@Getter
public class SchedulerUpdateRequest {
    private String username;
    private String title;
    private String content;
}
