package com.udemy.petsted.comment.controller;

import com.udemy.petsted.comment.dto.request.CommentCreateRequestDto;
import com.udemy.petsted.comment.dto.request.CommentUpdateRequestDto;
import com.udemy.petsted.comment.dto.response.CommentCreateResponseDto;
import com.udemy.petsted.comment.dto.response.CommentInquiryResponseDto;
import com.udemy.petsted.comment.dto.response.CommentUpdateResponseDto;
import com.udemy.petsted.comment.service.CommentService;
import com.udemy.petsted.common.dto.ApiResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{board-id}")/*시큐리티에 저장된 유저 정보 가져와야 함*/
    public ResponseEntity<?> createComment(@PathVariable("board-id") Long boardId,
        @RequestBody CommentCreateRequestDto createRequestDto) {
        CommentCreateResponseDto createResponseDto = commentService.create(createRequestDto);
        ApiResponseDto<?> apiResponseDto = ApiResponseDto.onSuccess(
            "댓글을 성공적으로 작성하였습니다.", createResponseDto);
        return ResponseEntity.ok(apiResponseDto);
    }

    @PutMapping("/{comment-id}")/*유저 정보 가져와야 함*/
    public ResponseEntity<?> updateComment(@PathVariable("comment-id") Long commentId,
        @RequestBody CommentUpdateRequestDto updateRequestDto) {
        CommentUpdateResponseDto updateResponseDto = commentService.update(commentId,
            updateRequestDto);
        ApiResponseDto<?> apiResponseDto = ApiResponseDto.onSuccess(
            "댓글을 성공적으로 수정하였습니다.", updateResponseDto);
        return ResponseEntity.ok(apiResponseDto);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity<?> deleteComment(@PathVariable("comment-id") Long commentId) {
        commentService.delete(commentId);
        ApiResponseDto<?> apiResponseDto = ApiResponseDto.onSuccess(
            "댓글을 성공적으로 삭제하였습니다.", null);
        return ResponseEntity.ok(apiResponseDto);
    }

    @GetMapping("/{last-comment-id}")
    public ResponseEntity<?> getComments(
        @PathVariable(value = "last-comment-id", required = false) Long lastCommentId) {
        List<CommentInquiryResponseDto> inquiryResponseDtoList = commentService.findAll(lastCommentId);
        ApiResponseDto<?> apiResponseDto = ApiResponseDto.onSuccess(
            "댓글을 성공적으로 조회하였습니다.", inquiryResponseDtoList);
        return ResponseEntity.ok(apiResponseDto);
    }
}
