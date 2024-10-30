package com.udemy.petsted.comment.dto.response;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateResponseDto {
    private Long commentId;
    private LocalDate createdAt;
}
