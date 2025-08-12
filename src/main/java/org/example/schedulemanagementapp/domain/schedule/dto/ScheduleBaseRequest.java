package org.example.schedulemanagementapp.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

// 일정 기본 요청 DTO
@Getter
@NoArgsConstructor
public class ScheduleBaseRequest {
    @NotNull
    private Long userId;
    @NotBlank(message = "제목 입력은 필수 입니다.")
    @Length(max = 10, message = "제목은 10글자 이하로 입력해주세요.")
    private String title;
    @NotBlank(message = "내용 입력은 필수 입니다.")
    @Length(max = 255, message = "내용은 255글자 이하로 입력해주세요.")
    private String content;
}
