package uib.lab.api.application.dto.bot;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BotUpdateRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String urlImagen;

    @NotBlank
    private String endpoint;
}
