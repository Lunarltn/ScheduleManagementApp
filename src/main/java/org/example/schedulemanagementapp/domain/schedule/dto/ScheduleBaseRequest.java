package org.example.schedulemanagementapp.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 일정 기본 요청 DTO
@Getter
@NoArgsConstructor
public class ScheduleBaseRequest {
    @NotNull
    private Long userId;
    @NotBlank(message = "제목 입력은 필수 입니다.")
    private String title;
    @NotBlank(message = "내용 입력은 필수 입니다.")
    private String content;
}
