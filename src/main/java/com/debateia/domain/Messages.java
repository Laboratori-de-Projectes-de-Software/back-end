package com.debateia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Messages {
    private Integer id;
    /* Anadir atributos necesarios para trabajar con el dominio que esten en las Entities/DTOs */
    private String contents;
    private LocalDateTime timestamp;
    private Integer botId;
    private Integer matchId;
}
