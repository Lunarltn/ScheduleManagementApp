package org.example.schedulemanagementapp.domain.comment.dto;

import lombok.Getter;
import org.example.schedulemanagementapp.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentBaseResponse {
    Long id;
    Long scheduleId;
    Long userId;
    String content;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    private CommentBaseResponse(Long id, Long scheduleId, Long userId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentBaseResponse of(Long id, Long scheduleId, Long userId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        return new CommentBaseResponse(id, scheduleId, userId, content, createdAt, modifiedAt);
    }

    public static CommentBaseResponse of(Comment comment) {
        return new CommentBaseResponse(
                comment.getId(),
                comment.getSchedule().getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
