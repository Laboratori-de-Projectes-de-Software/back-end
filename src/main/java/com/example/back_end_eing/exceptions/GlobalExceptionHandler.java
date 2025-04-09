package com.example.back_end_eing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExists(){
        ApiError error = new ApiError(ErrorCodes.USER_ALREADY_EXISTS);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExists(){
        ApiError error = new ApiError(ErrorCodes.EMAIL_ALREADY_EXISTS);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
