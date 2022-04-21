package com.example.user.exceptions;

public class ApiBadRequest extends RuntimeException{
    public ApiBadRequest(String message) {
        super(message);
    }
}
