package org.example.backend.databaseapi.application.dto.bot;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class BotDTORequest {

    private String name;
    private String description;
    private String urlImagen;
    private String endpoint;
}
