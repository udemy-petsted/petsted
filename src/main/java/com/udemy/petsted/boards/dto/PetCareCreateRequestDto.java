package com.udemy.petsted.boards.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PetCareCreateRequestDto {
    private Integer Siteuser;
    @NotBlank(message = "내용이 비어있습니다.")
    private String content;
    private String hashtag;

    @NotBlank(message = "지역을 선택해주세요")
    private String region;

    @NotBlank(message = "잃어버린 날짜와 시간을 선택해주세요 (시작 시간)")
    private LocalDateTime from;
    @NotBlank(message = "잃어버린 날짜와 시간을 선택해주세요 (종료 시간)")
    private LocalDateTime to;
}
