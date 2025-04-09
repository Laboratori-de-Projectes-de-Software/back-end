package com.example.back_end_eing.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError{
    private int code;
    private String message;
    private String type;

    public ApiError(ErrorCodes error){
        this.code = error.getCode();
        this.message = error.getMessage();
        this.type = error.getType();
    }
}
