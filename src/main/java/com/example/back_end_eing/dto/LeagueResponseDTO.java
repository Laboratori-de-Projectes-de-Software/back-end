package com.example.back_end_eing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeagueResponseDTO {
    private Integer leagueId;
    private String status;
    private String name;
    private String user; //owner
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Integer> bots;
}
