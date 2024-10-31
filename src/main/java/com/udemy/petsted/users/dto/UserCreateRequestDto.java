package com.udemy.petsted.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {
    @NotBlank(message = "닉네임은 채워져있어야합니다.")
    private String nickname;

    @NotBlank(message = "아이디는 채워져있어야힙니다.")
    private String username;

    @NotBlank(message = "패스워드는 채워져있어야합니다.")
    private String password;
    private String email;
    private boolean hasPet;
    private String profileUrl;
    private String phoneNumber;
    private String birthDate;
    private String region;
    private Double manner;

}
