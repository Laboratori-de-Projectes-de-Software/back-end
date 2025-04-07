package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BotResponseDTO {
    private int botId;
    private String name;
    private String description;
    private String urlImage;
    private int nWins;
    private int nLoses;
    private int nDraws;
}
