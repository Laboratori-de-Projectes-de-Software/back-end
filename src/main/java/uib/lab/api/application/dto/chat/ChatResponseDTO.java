package uib.lab.api.application.dto.chat;

import lombok.Getter;
import lombok.Setter;
import uib.lab.api.infraestructure.jpaEntity.League;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ChatResponseDTO {
    
    public ChatResponseDTO(String text, String time, int botId){
        this.text = text;
        this.time = time;
        this.botId = botId;
    }

    @NotBlank
    private String text;

    @NotBlank
    private String time;

    @NotNull
    private int botId;

}
