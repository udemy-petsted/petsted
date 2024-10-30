package com.udemy.petsted.common.dto;

import lombok.Builder;

@Builder
public class ApiResponseDto<T> {

    private String status;
    private String message;
    private T data;

    public static <T> ApiResponseDto<T> of(String status, String message, T data) {
        return ApiResponseDto.<T>builder()
            .status(status)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> ApiResponseDto<T> onSuccess(String message, T data) {
        return ApiResponseDto.of("success", message, data);
    }
}