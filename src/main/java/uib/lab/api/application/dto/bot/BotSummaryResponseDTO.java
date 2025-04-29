package uib.lab.api.application.dto.bot;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BotSummaryResponseDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private int id;

    public BotSummaryResponseDTO(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }
}
