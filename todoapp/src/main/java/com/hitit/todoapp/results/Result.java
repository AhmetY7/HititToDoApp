package com.hitit.todoapp.results;

import lombok.Getter;

public class Result {
    private final boolean success;
    @Getter
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
