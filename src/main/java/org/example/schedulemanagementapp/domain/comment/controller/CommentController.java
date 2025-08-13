package org.example.schedulemanagementapp.domain.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagementapp.domain.comment.dto.CommentBaseRequest;
import org.example.schedulemanagementapp.domain.comment.dto.CommentBaseResponse;
import org.example.schedulemanagementapp.domain.comment.service.CommentService;
import org.example.schedulemanagementapp.domain.user.dto.UserBaseResponse;
import org.example.schedulemanagementapp.global.constant.Const;
import org.example.schedulemanagementapp.global.util.SessionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 관련 요청을 처리하는 컨트롤러
 *
 * <p>요청을 받아 댓글 저장, 댓글 조회, 부분 조회, 수정, 삭제 기능 제공</p>
 */
@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 새로운 댓글 저장
     *
     * @param requestDto 댓글 기본 요청 DTO
     * @param scheduleId 일정 아이디
     * @return HTTP 상태 코드와 댓글 기본 응답 DTO
     */
    @PostMapping
    public ResponseEntity<CommentBaseResponse> save(@Valid @RequestBody CommentBaseRequest requestDto,
                                                    @PathVariable Long scheduleId,
                                                    HttpServletRequest request) {
        // 로그인 유저 아이디 확인
        Long loginUserId = SessionUtils.GetLoginUserIdBySelvet(request);

        return new ResponseEntity<>(commentService.save(loginUserId, scheduleId, requestDto), HttpStatus.CREATED);
    }

    /**
     * 일정 전체 댓글 조회
     *
     * @param scheduleId 일정 아이디
     * @return HTTP 상태 코드와 댓글 기본 응답 DTO의 List
     */
    @GetMapping
    public ResponseEntity<List<CommentBaseResponse>> findAllByScheduleId(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.findAllByScheduleId(scheduleId));
    }

    /**
     * 아이디 댓글 조회
     *
     * @param scheduleId 일정 아이디
     * @param commentId  댓글 아이디
     * @return HTTP 상태 코드와 댓글 기본 응답 DTO
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentBaseResponse> findCommentById(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.findCommentById(scheduleId, commentId));
    }

    /**
     * 댓글 수정
     *
     * @param scheduleId 일정 아이디
     * @param commentId  댓글 아이디
     * @param requestDto 댓글 기본 요청 DTO
     * @param request    HTTP 요청 정보
     * @return HTTP 상태 코드와 댓글 기본 응답 DTO
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentBaseResponse> updateCommentById(@PathVariable Long scheduleId,
                                                                 @PathVariable Long commentId,
                                                                 @Valid @RequestBody CommentBaseRequest requestDto,
                                                                 HttpServletRequest request) {
        // 로그인 유저 아이디 확인
        Long loginUserId = SessionUtils.GetLoginUserIdBySelvet(request);

        return ResponseEntity.ok(commentService.updateCommentById(loginUserId, scheduleId, commentId, requestDto));
    }

    /**
     * 댓글 삭제
     *
     * @param scheduleId 일정 아이디
     * @param commentId  댓글 아이디
     * @param request    HTTP 요청 정보
     * @return HTTP 상태 코드
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long scheduleId,
                                                  @PathVariable Long commentId,
                                                  HttpServletRequest request) {
        // 로그인 유저 아이디 확인
        Long loginUserId = SessionUtils.GetLoginUserIdBySelvet(request);

        commentService.deleteCommentById(loginUserId, scheduleId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
