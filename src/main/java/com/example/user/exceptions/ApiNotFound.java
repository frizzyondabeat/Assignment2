package com.example.user.exceptions;

public class ApiNotFound extends RuntimeException{
    public ApiNotFound(String message) {
        super(message);
    }
}
