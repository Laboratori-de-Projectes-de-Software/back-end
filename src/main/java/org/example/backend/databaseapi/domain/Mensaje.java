package org.example.backend.databaseapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Mensaje {

    private Integer mensajeId;
    private Bot bot;
    private Partida partida;
    private String texto; //Notnull
    private Timestamp hora; //Notnull

}
