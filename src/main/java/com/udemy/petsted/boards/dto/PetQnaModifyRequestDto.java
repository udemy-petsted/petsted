package com.udemy.petsted.boards.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PetQnaModifyRequestDto {
    @NotBlank(message = "제목이 비어있습니다.")
    private String title;
}
