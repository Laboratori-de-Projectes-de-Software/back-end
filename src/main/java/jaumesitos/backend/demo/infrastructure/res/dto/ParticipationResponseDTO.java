package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ParticipationResponseDTO {
    Integer botid;
    String name;
    Integer points;
    Integer position;
    Integer wins;
    Integer draws;
    Integer losses;
}
