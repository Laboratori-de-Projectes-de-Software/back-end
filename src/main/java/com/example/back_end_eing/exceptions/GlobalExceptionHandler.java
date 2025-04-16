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
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExists(){
        ApiError error = new ApiError(ErrorCodes.EMAIL_ALREADY_EXISTS);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNameNotFound(){
        ApiError error = new ApiError(ErrorCodes.USER_NAME_NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIncorrectPassword(){
        ApiError error = new ApiError(ErrorCodes.INCORRECT_PASSWORD);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RepeatedBotException.class)
    public ResponseEntity<ApiError> handleBotAlready(){
        ApiError error = new ApiError(ErrorCodes.BOT_ALREADY_EXISTS);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(){
        ApiError error = new ApiError(ErrorCodes.USER_NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BotNotFoundException.class)
    public ResponseEntity<ApiError> handleBotNotFound(){
        ApiError error = new ApiError(ErrorCodes.BOT_NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LigaNotFoundException.class)
    public ResponseEntity<ApiError> handleLigaNotFound(){
        ApiError error = new ApiError(ErrorCodes.LEAGUE_NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(IncorrectNumBotsException.class)
    public ResponseEntity<ApiError> handleIncorrectNumBots(){
        ApiError error = new ApiError(ErrorCodes.INCORRECT_NUM_BOTS);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClasificacionNotFoundException.class)
    public ResponseEntity<ApiError> handleClasificacionNotFound(){
        ApiError error = new ApiError(ErrorCodes.CLASIFICATION_NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
