package com.debateia.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Messages {
    /* Anadir atributos necesarios para trabajar con el dominio que esten en las Entities/DTOs */
    private String contents;
    private Date timestamp;
    private Bot bot;
    private Match match;


}
