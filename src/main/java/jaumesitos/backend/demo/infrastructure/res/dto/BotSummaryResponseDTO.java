package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BotSummaryResponseDTO {
    private String name;
    private String description;
    private Integer id;
}
