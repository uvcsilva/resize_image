package com.example.resize_image.exceptions;

public class NotDirectoryException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public NotDirectoryException(String msg) {
        super(msg);
    }
}
