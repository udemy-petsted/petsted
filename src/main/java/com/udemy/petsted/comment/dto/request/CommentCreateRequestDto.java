package com.udemy.petsted.comment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequestDto {
    //@태그 필요할 거 같음
    private String content;
}
