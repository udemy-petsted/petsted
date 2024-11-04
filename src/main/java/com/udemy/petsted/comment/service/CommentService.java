package com.udemy.petsted.comment.service;

import com.udemy.petsted.comment.dto.request.CommentCreateRequestDto;
import com.udemy.petsted.comment.dto.request.CommentUpdateRequestDto;
import com.udemy.petsted.comment.dto.response.CommentCreateResponseDto;
import com.udemy.petsted.comment.dto.response.CommentInquiryResponseDto;
import com.udemy.petsted.comment.dto.response.CommentUpdateResponseDto;
import com.udemy.petsted.comment.entity.CommentEntity;
import com.udemy.petsted.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentCreateResponseDto create(CommentCreateRequestDto createRequestDto) {
        CommentEntity comment = CommentEntity.of(createRequestDto);
        commentRepository.save(comment);
        return CommentCreateResponseDto.from(comment);
    }

    @Transactional
    public CommentUpdateResponseDto update(Long commentId,
        CommentUpdateRequestDto updateRequestDto) {
        CommentEntity comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.modify(updateRequestDto);
        return CommentUpdateResponseDto.from(comment);
    }

    public void delete(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.findById(commentId);
        //유저 검증
        commentRepository.delete(comment);
    }

    public List<CommentInquiryResponseDto> findAll(Long lastCommentId) {
        List<CommentEntity> comments = commentRepository.findAllByLastId(lastCommentId);
        return comments.stream().map(CommentInquiryResponseDto::from).toList();
    }
}
