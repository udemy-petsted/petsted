package com.udemy.petsted.auth.exception;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;

@Getter
public class UserTaskException extends RuntimeException {

    private final String msg;
    private final int code;

    public UserTaskException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

}
