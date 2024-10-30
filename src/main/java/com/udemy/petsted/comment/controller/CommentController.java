package com.udemy.petsted.comment.controller;

import com.udemy.petsted.comment.dto.request.CommentCreateRequestDto;
import com.udemy.petsted.comment.dto.response.CommentCreateResponseDto;
import com.udemy.petsted.comment.service.CommentService;
import com.udemy.petsted.common.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{board-id}")/*시큐리티에 저장된 유저 정보 가져와야 함*/
    public ResponseEntity<?> createComment(@PathVariable("board-id") String boardId,
        @RequestBody CommentCreateRequestDto createRequestDto) {
        CommentCreateResponseDto createResponseDto = commentService.create(createRequestDto);
        ApiResponseDto<?> apiResponseDto = ApiResponseDto.onSuccess(
            "댓글을 성공적으로 작성하였습니다.", createResponseDto);
        return ResponseEntity.ok(apiResponseDto);
    }
}
