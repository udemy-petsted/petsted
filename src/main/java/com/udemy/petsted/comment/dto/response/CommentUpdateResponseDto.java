package com.udemy.petsted.comment.dto.response;

import com.udemy.petsted.comment.entity.CommentEntity;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentUpdateResponseDto {
    private LocalDate updatedAt;

    public static CommentUpdateResponseDto from(CommentEntity comment) {
        return CommentUpdateResponseDto.builder()
            .updatedAt(comment.getUpdatedAt())
            .build();
    }
}
