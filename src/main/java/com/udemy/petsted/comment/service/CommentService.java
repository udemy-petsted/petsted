package com.udemy.petsted.comment.service;

import com.udemy.petsted.comment.dto.request.CommentCreateRequestDto;
import com.udemy.petsted.comment.dto.response.CommentCreateResponseDto;
import com.udemy.petsted.comment.entity.CommentEntity;
import com.udemy.petsted.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
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
        return comment.toCreateResponseDto();
    }
}
