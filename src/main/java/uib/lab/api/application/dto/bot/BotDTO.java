package uib.lab.api.application.dto.bot;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BotDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String urlImagen;

    @NotBlank
    private String endpoint;

    @NotNull
    private int userId;

}