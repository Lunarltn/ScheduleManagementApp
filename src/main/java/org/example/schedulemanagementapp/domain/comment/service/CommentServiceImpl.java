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
        // 일정 아이디 검사
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
        List<Comment> comments = commentRepository.findAllBySchedule(schedule);

        return comments.stream().map(CommentBaseResponse::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CommentBaseResponse findCommentById(Long scheduleId, Long commentId) {
        // 일정 아이디 검사
        if (notExistsScheduleIdInScheduleRepository(scheduleId))
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);

        Comment comment = findCommentByIdOrThrow(commentId);

        // 댓글 엔티티의 일정 아이디값과 입력받은 일정 아이디 검사
        if (notExistsScheduleIdInComment(comment, scheduleId))
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        return commentRepository.findById(commentId).map(CommentBaseResponse::of).orElse(null);
    }

    @Transactional
    @Override
    public CommentBaseResponse updateCommentById(Long userId, Long scheduleId, Long commentId, CommentBaseRequest requestDto) {
        // 일정 아이디 검사
        if (notExistsScheduleIdInScheduleRepository(scheduleId))
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);

        Comment comment = findCommentByIdOrThrow(commentId);

        // 댓글 엔티티의 일정 아이디값과 입력받은 일정 아이디 검사
        if (notExistsScheduleIdInComment(comment, scheduleId))
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);

        // 유저 검증
        if (notExistsUserIdInComment(comment, userId))
            throw new CustomException(ErrorCode.NO_PERMISSION);

        comment.setContent(requestDto.getContent());

        return CommentBaseResponse.of(comment);
    }

    @Transactional
    @Override
    public void deleteCommentById(Long userId, Long scheduleId, Long commentId) {
        // 일정 아이디 검사
        if (notExistsScheduleIdInScheduleRepository(scheduleId))
            throw new CustomException(ErrorCode.SCHEDULE_NOT_FOUND);

        Comment comment = findCommentByIdOrThrow(commentId);

        // 댓글 엔티티의 일정 아이디값과 입력받은 일정 아이디 검사
        if (notExistsScheduleIdInComment(comment, scheduleId))
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);

        // 유저 검증
        if (notExistsUserIdInComment(comment, userId))
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
     * 댓글 엔티티의 유저 아이디와 매개 변수로 받은 유저 아이디가 다른지 여부 반환
     *
     * @param comment 검사할 댓글 엔티티
     * @param userId  유저 아이디
     * @return 다르면 ture, 같다면 false
     */
    private boolean notExistsUserIdInComment(Comment comment, Long userId) {
        return !Objects.equals(comment.getUser().getId(), userId);
    }

    /**
     * 댓글 엔티티의 일정 아이디와 매개 변수로 받은 일정 아이디가 다른지 여부 반환
     *
     * @param comment    검사할 댓글 엔티티
     * @param scheduleId 일정 아이디
     * @return 다르면 ture, 같다면 false
     */
    private boolean notExistsScheduleIdInComment(Comment comment, Long scheduleId) {
        return !Objects.equals(comment.getSchedule().getId(), scheduleId);
    }

    /**
     * 일정 레포지토리에 매개 변수로 받은 일정 아이디값이 존재하는지 여부 반환
     *
     * @param scheduleId 일정 아이디
     * @return 없다면 ture, 있다면 false
     */
    private boolean notExistsScheduleIdInScheduleRepository(Long scheduleId) {
        return !scheduleRepository.existsById(scheduleId);
    }
}
