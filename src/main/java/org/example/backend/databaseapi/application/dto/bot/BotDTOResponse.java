package org.example.backend.databaseapi.application.dto.bot;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class BotDTOResponse {

    private Integer botId;
    private String name;
    private String description;
    private String urlImagen;
    private Integer nWins;
    private Integer nDraws;
    private Integer nLosses;

}
