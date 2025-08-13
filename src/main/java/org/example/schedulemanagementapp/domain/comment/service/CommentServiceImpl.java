package org.example.schedulemanagementapp.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.comment.dto.CommentBaseRequest;
import org.example.schedulemanagementapp.domain.comment.dto.CommentBaseResponse;
import org.example.schedulemanagementapp.domain.comment.entity.Comment;
import org.example.schedulemanagementapp.domain.comment.repository.CommentRepository;
import org.example.schedulemanagementapp.domain.schedule.entity.Schedule;
import org.example.schedulemanagementapp.domain.schedule.repository.ScheduleRepository;
import org.example.schedulemanagementapp.domain.schedule.service.ScheduleService;
import org.example.schedulemanagementapp.domain.user.entity.User;
import org.example.schedulemanagementapp.domain.user.repository.UserRepository;
import org.example.schedulemanagementapp.domain.user.service.UserService;
import org.example.schedulemanagementapp.global.exception.CustomException;
import org.example.schedulemanagementapp.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@link CommentService}를 구현체
 *
 * <p>JPA를 사용하여 데이터를 데이터베이스에 저장, 조회, 수정, 삭제하는 기능 제공</p>
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CommentBaseResponse save(Long userId, Long scheduleId, CommentBaseRequest requestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND)
        );

        Comment comment = new Comment(
                user,
                schedule,
                requestDto.getContent()
        );

        Comment savedComment = commentRepository.save(comment);

        return CommentBaseResponse.of(savedComment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentBaseResponse> findAllByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
        List<Comment> comments = commentRepository.findAllBySchedule(schedule);

        return comments.stream().map(CommentBaseResponse::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CommentBaseResponse findCommentById(Long scheduleId, Long commentId) {
        Comment comment = findCommentByIdOrThrow(commentId);
        // 일정 아이디 검사
        if (!CheckScheduleIdInComment(comment, scheduleId))
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        return commentRepository.findById(commentId).map(CommentBaseResponse::of).orElse(null);
    }

    @Transactional
    @Override
    public CommentBaseResponse updateCommentById(Long userId, Long scheduleId, Long commentId, CommentBaseRequest requestDto) {
        Comment comment = findCommentByIdOrThrow(commentId);
        // 일정 아이디 검사
        if (!CheckScheduleIdInComment(comment, scheduleId))
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        // 유저 검증
        if (!CheckUserIdInComment(comment, userId))
            throw new CustomException(ErrorCode.NO_PERMISSION);

        comment.setContent(requestDto.getContent());

        return CommentBaseResponse.of(comment);
    }

    @Transactional
    @Override
    public void deleteCommentById(Long userId, Long scheduleId, Long commentId) {
        Comment comment = findCommentByIdOrThrow(commentId);
        // 일정 아이디 검사
        if (!CheckScheduleIdInComment(comment, scheduleId))
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);
        // 유저 검증
        if (!CheckUserIdInComment(comment, userId))
            throw new CustomException(ErrorCode.NO_PERMISSION);

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Comment findCommentByIdOrThrow(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );
    }

    /**
     * 댓글 작성자 아이디를 확인하고 비교값 반환
     *
     * @param comment 댓글 엔티티
     * @param userId  유저 아이디
     */
    private boolean CheckUserIdInComment(Comment comment, Long userId) {
        return Objects.equals(comment.getUser().getId(), userId);
    }

    /**
     * 댓글 일정 아이디를 확인하고 비교값 반환
     *
     * @param comment    댓글 엔티티
     * @param scheduleId 일정 아이디
     */
    private boolean CheckScheduleIdInComment(Comment comment, Long scheduleId) {
        return Objects.equals(comment.getSchedule().getId(), scheduleId);
    }
}
