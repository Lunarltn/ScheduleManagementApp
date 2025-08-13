package org.example.schedulemanagementapp.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CommentBaseRequest {
    @NotBlank(message = "내용 입력은 필수입니다.")
    @Length(max = 255, message = "내용은 255글자 이하로 입력해주세요.")
    private String content;
}
