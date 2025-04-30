package org.example.backend.databaseapi.application.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomException {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseBody
    public final ErrorInfo ResourceAlreadyExistsException(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL(), ex);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(MetodoNoPermitido.class)
    @ResponseBody
    public final ErrorInfo MetodoNoPermitido(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL(), ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public final ErrorInfo ResourceNotFoundException(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL(), ex);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseBody
    public final ErrorInfo IncorrectCredentialsException(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL(), ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class, IllegalArgumentException.class})
    @ResponseBody
    public final ErrorInfo handleValidationException(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURL(), ex);
    }
}
