package com.example.resize_image.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {

    private int statusCode;
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorMessage(int statusCode, String error, String message, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

}
