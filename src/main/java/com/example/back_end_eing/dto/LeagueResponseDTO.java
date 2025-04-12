package com.example.back_end_eing.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@Getter
@Setter
public class LeagueResponseDTO {
    private Integer leagueId;
    private String state;
    private String name;
    private String user; //owner
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Integer> bots;
}
