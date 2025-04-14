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
    Integer points;
    Integer matches;
    Integer wins;
    Integer draws;
    Integer losses;
}
