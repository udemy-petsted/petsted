package com.udemy.petsted.boards.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BoardCreateRequestDto {

    private Integer Siteuser;
    @NotBlank(message = "내용이 비어있습니다.")
    private String content;
    private String hashtag;

    @NotBlank(message = "게시글 타입을 선택해주세요")
    private Integer post_type;
}
