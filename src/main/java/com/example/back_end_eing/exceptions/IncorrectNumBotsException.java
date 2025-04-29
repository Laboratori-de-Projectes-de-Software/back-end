package com.example.back_end_eing.exceptions;

public class IncorrectNumBotsException extends RuntimeException {
    public IncorrectNumBotsException(Integer numBots) {
        super(numBots + " es un número de bots incorrecto. Debe ser un número par");
    }
}
