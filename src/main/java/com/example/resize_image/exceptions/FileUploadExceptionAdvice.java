package com.example.resize_image.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.file.NotDirectoryException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorMessage> MaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ErrorMessage(HttpStatus.EXPECTATION_FAILED.value(),"MaxUploadSizeExceededException",
                        "Arquivo acima do limite permitido", LocalDateTime.now()));
    }

    @ExceptionHandler(NotDirectoryException.class)
    public ResponseEntity<ErrorMessage> NotDirectoryException(NotDirectoryException ex) {

        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "NotDirectoryException",
                ex.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ErrorMessage> FileNotFoundException(FileNotFoundException ex) {

        ErrorMessage message = new ErrorMessage(
                HttpStatus.EXPECTATION_FAILED.value(),
                "FileNotFoundException",
                ex.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public ResponseEntity<ErrorMessage> UnsupportedEncodingException(IOException ex) {

        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "UnsupportedEncodingException",
                ex.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
