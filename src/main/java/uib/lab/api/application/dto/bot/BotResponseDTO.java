package uib.lab.api.application.dto.bot;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotResponseDTO {

    public BotResponseDTO(int id, String name, String description, String urlImagen, int nWins, int nLoses, int nDraws) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlImagen = urlImagen;
        this.nWins = nWins;
        this.nLoses = nLoses;
        this.nDraws = nDraws;
    }

    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String urlImagen;

    @NotNull
    private int nWins;

    @NotNull
    private int nLoses;

    @NotNull
    private int nDraws;
}
