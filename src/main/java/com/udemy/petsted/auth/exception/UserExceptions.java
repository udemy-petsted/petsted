package com.udemy.petsted.auth.exception;

public enum UserExceptions {

    NOT_FOUND("NOT_FOUND", 404),
    DUPLICATE("DUPLICATE", 409),
    INVALID("INVALID", 400),
    ;

    private final UserTaskException userTaskException;

    UserExceptions(String msg, int code) {
        userTaskException = new UserTaskException(msg, code);
    }

    public UserTaskException get() {
        return userTaskException;
    }
}
