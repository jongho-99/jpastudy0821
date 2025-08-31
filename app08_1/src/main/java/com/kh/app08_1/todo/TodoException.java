package com.kh.app08_1.todo;

public class TodoException extends RuntimeException {

    public TodoException() {
    }

    public TodoException(String message) {
        super(message);
    }
}
