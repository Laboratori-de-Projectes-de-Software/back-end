package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BotDTO {
    private String name;
    private String description;
    private String urlImage;
    private String endpoint;
    private int ownerId;
}
