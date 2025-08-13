package org.example.schedulemanagementapp.domain.comment.service;

import jakarta.validation.Valid;
import org.example.schedulemanagementapp.domain.comment.dto.CommentBaseRequest;
import org.example.schedulemanagementapp.domain.comment.dto.CommentBaseResponse;
import org.example.schedulemanagementapp.domain.comment.entity.Comment;

import java.util.List;

/**
 * 댓글 관련 로직을 처리하는 서비스 인터페이스
 *
 * <p>댓글 저장, 전체 조회, 부분 조회, 수정, 삭제 기능 정의</p>
 */
public interface CommentService {
    /**
     * 새로운 댓글 저장
     *
     * @param userId     유저 아이디
     * @param scheduleId 일정 아이디
     * @param requestDto 댓글 기본 요청 DTO
     * @return 댓글 기본 응답 DTO
     */
    CommentBaseResponse save(Long userId, Long scheduleId, CommentBaseRequest requestDto);

    /**
     * 일정 전체 댓글 조회
     *
     * @param scheduleId 일정 아이디
     * @return 댓글 기본 응답 DTO의 List
     */
    List<CommentBaseResponse> findAllByScheduleId(Long scheduleId);

    /**
     * 아이디 댓글 조회
     *
     * @param commentId 댓글 아이디
     * @return 댓글 기본 응답 DTO
     */
    CommentBaseResponse findCommentById(Long scheduleId, Long commentId);

    /**
     * 댓글 수정
     *
     * @param userId     유저 아이디
     * @param commentId  댓글 아이디
     * @param requestDto 댓글 기본 요청 DTO
     * @return 댓글 기본 응답 DTO
     */
    CommentBaseResponse updateCommentById(Long userId, Long scheduleId, Long commentId, CommentBaseRequest requestDto);

    /**
     * 댓글 삭제
     *
     * @param userId    유저 아이디
     * @param commentId 댓글 아이디
     */
    void deleteCommentById(Long userId, Long scheduleId, Long commentId);

    /**
     * 레포지토리에서 아이디로 댓글 엔티티를 찾아 반환하고 없다면 예외 발생
     *
     * @param commentId 댓글 아이디
     * @return 댓글 엔티티
     */
    Comment findCommentByIdOrThrow(Long commentId);

}
