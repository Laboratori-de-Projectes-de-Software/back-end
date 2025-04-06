package org.example.backend.databaseapi.application.dto.liga;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class LeagueDTORequest {

    private String name;
    private String urlImagen;
    private Integer rounds;
    private Long matchTime;
    private List<Integer> bots;

}
