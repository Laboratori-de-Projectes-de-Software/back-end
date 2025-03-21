package uib.lab.api.dto.League;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class LeagueRequest {

    @NotBlank(message = "El nombre de la liga es obligatorio")
    private String name;

    @NotNull(message = "La fecha de la liga es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") 
    private LocalDateTime date;

    @NotNull(message = "El tiempo de juego es obligatorio")
    private int playTime;

    @NotNull(message = "El número de rondas es obligatorio")
    private int numRounds;
}
