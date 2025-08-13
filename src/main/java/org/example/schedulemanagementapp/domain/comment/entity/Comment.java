package org.example.schedulemanagementapp.domain.comment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;
import org.example.schedulemanagementapp.domain.user.entity.User;
import org.example.schedulemanagementapp.global.entity.BaseEntity;

/**
 * 댓글 엔티티
 *
 * <p>사용자 ID, 일정 ID, 내용,  생성/수정 시간을 관리한다</p>
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @Setter
    private String content;

    public Comment(User user, Schedule schedule, String content) {
        this.user = user;
        this.schedule = schedule;
        this.content = content;
    }
}
