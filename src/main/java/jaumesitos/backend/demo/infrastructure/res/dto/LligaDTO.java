package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor

public class LligaDTO {
    LocalDateTime data;
    String nom;
    Boolean estat;

}
