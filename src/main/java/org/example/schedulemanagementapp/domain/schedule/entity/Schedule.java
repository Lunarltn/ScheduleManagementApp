package org.example.schedulemanagementapp.domain.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulemanagementapp.domain.user.entity.User;
import org.example.schedulemanagementapp.global.entity.BaseEntity;
import org.example.schedulemanagementapp.domain.comment.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * 일정 엔티티
 *
 * <p>사용자 ID, 제목, 내용,  생성/수정 시간을 관리한다</p>
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @Column(nullable = false, length = 10)
    private String title;
    @Column(nullable = false)
    private String content;

    public Schedule(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
