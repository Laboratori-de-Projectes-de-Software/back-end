package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uib.lab.api.application.dto.bot.BotDTO;
import uib.lab.api.application.dto.bot.BotResponseDTO;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.application.port.BotPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.UserDomain;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotPort botPort;
    private final UserPort userPort;

    public List<BotDomain> getBotsByUser(int userId) {
        UserDomain user = userPort.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return botPort.findAllByUser(user);
    }

    public BotResponseDTO save(BotDTO botDTO, Locale locale) {
        UserDomain user = userPort.findById(botDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + botDTO.getUserId()));

        BotDomain bot = new BotDomain();
        bot.setIdeologia(botDTO.getName());
        bot.setDescription(botDTO.getDescription());
        bot.setUrlImagen(botDTO.getUrlImagen());
        bot.setUrl(botDTO.getEndpoint());
        bot.setUserId(user.getId());

        bot = botPort.save(bot);

        return new BotResponseDTO(
                bot.getId(),
                bot.getIdeologia(),
                bot.getDescription(),
                bot.getUrlImagen(),
                0,
                0,
                0
        );
    }
}