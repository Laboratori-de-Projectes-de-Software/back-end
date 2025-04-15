package com.example.back_end_eing.dto;


import com.example.back_end_eing.models.Liga;
import lombok.*;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LeagueResponseDTO {
    private Integer leagueId;
    private String status;
    private String name;
    private String user; //owner
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Long> bots;


    public LeagueResponseDTO(Liga liga, List<Long> bots) {

        this.leagueId = Math.toIntExact(liga.getId());
        this.status = liga.getEstado();
        this.name = liga.getNombreLiga();
        this.user = liga.getUsuario().getNombreUsuario();
        this.urlImagen = liga.getImagen();
        this.bots = bots;
        this.rounds = liga.getNumJornadas();
        this.matchTime = liga.getMatchTime() * 60;

    }
}