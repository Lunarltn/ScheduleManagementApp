package org.example.schedulemanagementapp.domain.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

// 일정 수정 요청 DTO
@Getter
@NoArgsConstructor
public class SchedulerUpdateRequest {
    private Long userId;
    @Length(max = 10, message = "제목은 10글자 이상 입력해주세요.")
    private String title;
    @Length(max = 255, message = "내용은 255글자 이하로 입력해주세요.")
    private String content;
}
