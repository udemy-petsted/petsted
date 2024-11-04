package com.udemy.petsted.comment.dto.response;

import com.udemy.petsted.comment.entity.CommentEntity;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateResponseDto {

    private Long commentId;
    private LocalDate createdAt;

    public static CommentCreateResponseDto from(CommentEntity comment) {
        return CommentCreateResponseDto.builder()
            .commentId(comment.getId())
            .createdAt(comment.getCreatedAt())
            .build();
    }
}
