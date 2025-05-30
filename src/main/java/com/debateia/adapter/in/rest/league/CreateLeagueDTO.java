package com.debateia.adapter.in.rest.league;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonTypeName("CreateLeagueDTO")
public class CreateLeagueDTO implements Serializable {
    private String name;
    private String urlImagen;
    private Integer rounds;
    private long matchMaxMessages;
    //private int userId;
}
