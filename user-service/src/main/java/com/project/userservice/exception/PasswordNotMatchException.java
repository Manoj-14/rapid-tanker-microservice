package com.project.userservice.exception;

public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
