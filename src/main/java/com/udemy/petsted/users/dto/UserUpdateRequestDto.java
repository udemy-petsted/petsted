package com.udemy.petsted.users.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto {
    private String nickname;

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;
    private Boolean hasPet;

    private String profileUrl;
    private String phoneNumber;
    private String birthDate;
    private String region;
}
