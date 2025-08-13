package org.example.schedulemanagementapp.domain.comment.repository;

import org.example.schedulemanagementapp.domain.comment.entity.Comment;
import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 댓글 레포지토리
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllBySchedule(Schedule schedule);
}
