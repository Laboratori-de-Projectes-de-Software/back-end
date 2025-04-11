package com.debateia.adapter.in.web.dto.request;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@JsonTypeName("LeagueDTO")
public class LeagueDTO implements Serializable {
    private String name;
    private String urlImagen;
    private Integer rounds;
    private long matchTime;
    private List<Integer> bots;
    private int userId;
}
