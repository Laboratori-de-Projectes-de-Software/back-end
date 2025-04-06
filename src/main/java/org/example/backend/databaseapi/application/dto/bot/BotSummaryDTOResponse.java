package org.example.backend.databaseapi.application.dto.bot;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class BotSummaryDTOResponse {

    private String nombre;
    private Integer id;
    private String cualidad;
}
