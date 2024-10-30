package com.udemy.petsted.boards.dto;

public class BoardEntity {
    public class ApiResponse<T> {
        private String status;
        private String message;
        private T data;

        public ApiResponse(String status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

    }

}
