package uib.lab.api.application.dto.chat;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChatDTO {
    
    @NotBlank
    private String text;

    @NotBlank
    private String time;

    @NotNull
    @Min(1)
    private int botId;

    @NotNull
    @Min(1)
    private int matchId;

}
