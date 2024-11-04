package com.udemy.petsted.comment.dto.response;

import com.udemy.petsted.comment.entity.CommentEntity;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentInquiryResponseDto {
    private Long commentId;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public static CommentInquiryResponseDto from(CommentEntity comment) {
        return CommentInquiryResponseDto.builder()
            .commentId(comment.getId())
            .content(comment.getContent())
            .createdAt(comment.getCreatedAt())
            .updatedAt(comment.getUpdatedAt())
            .build();
    }

}
