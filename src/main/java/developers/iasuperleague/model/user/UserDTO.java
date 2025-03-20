package developers.iasuperleague.model.user;

import developers.iasuperleague.model.bot.BotDTO;
import developers.iasuperleague.model.liga.LigaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private List<LigaDTO> ligasCreadasDTO;

    private List<BotDTO> botsDTO;
}
