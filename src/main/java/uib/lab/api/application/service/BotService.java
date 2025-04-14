package uib.lab.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uib.lab.api.application.dto.bot.BotDTO;
import uib.lab.api.application.dto.bot.BotResponseDTO;
import uib.lab.api.application.dto.bot.BotSummaryResponseDTO;
import uib.lab.api.application.port.UserPort;
import uib.lab.api.application.port.BotPort;
import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.util.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BotService {
    private final BotPort botPort;
    private final UserPort userPort;

    public ApiResponse<BotResponseDTO> save(BotDTO botDTO) {
        try {

            UserDomain user = userPort.findById(botDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + botDTO.getUserId()));

            BotDomain bot = new BotDomain();
            bot.setIdeologia(botDTO.getName());
            bot.setName(botDTO.getName());
            bot.setDescription(botDTO.getDescription());
            bot.setUrlImagen(botDTO.getUrlImagen());
            bot.setUrl(botDTO.getEndpoint());
            bot.setUserId(user.getId());
            bot.setNWins(0);
            bot.setNLosses(0);
            bot.setNDraws(0);
            bot = botPort.save(bot);
            BotResponseDTO botResponseDTO = new BotResponseDTO(
                    bot.getId(),
                    bot.getIdeologia(),
                    bot.getDescription(),
                    bot.getUrlImagen(),
                    bot.getNWins(),
                    bot.getNLosses(),
                    bot.getNDraws()
            );

            return new ApiResponse<>(201, "Bot created", botResponseDTO);

        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "User not found");
        } catch (ResponseStatusException ex) {
            if (ex.getStatus() == HttpStatus.CONFLICT) {
                return new ApiResponse<>(409, "Bot conflict");
            }
            return new ApiResponse<>(500, "Internal Server Error");
        } catch (Exception ex) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }

    public ApiResponse<List<BotSummaryResponseDTO>> getBotsByUser(Integer owner) {
        try {
            List<BotSummaryResponseDTO> botList;

            if (owner != null) {
                UserDomain user = userPort.findById(owner)
                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + owner));

                botList = botPort.findAllByUser(user)
                        .stream()
                        .map(bot -> new BotSummaryResponseDTO(bot.getIdeologia(),bot.getDescription(),bot.getId()))
                        .collect(Collectors.toList());
            } else {
                botList = botPort.findAll()
                        .stream()
                        .map(bot -> new BotSummaryResponseDTO(bot.getIdeologia(),bot.getDescription(),bot.getId()))
                        .collect(Collectors.toList());
            }

            if (!botList.isEmpty()) {
                return new ApiResponse(200, "Bots found", botList);
            } else {
                return new ApiResponse(200, "No bots found", new ArrayList<>());
            }
        } catch (IllegalArgumentException e) {
            return new ApiResponse(404, "User not found");
        } catch (Exception e) {
            return new ApiResponse(500, "Internal Server Error");
        }
    }

    public ApiResponse<BotResponseDTO> getBotById(Integer botId) {
        try {
            BotDomain bot = botPort.findById(botId)
                    .orElseThrow(() -> new IllegalArgumentException("Bot not found with ID: " + botId));

            BotResponseDTO botResponseDTO = new BotResponseDTO(
                    bot.getId(),
                    bot.getIdeologia(),
                    bot.getDescription(),
                    bot.getUrlImagen(),
                    bot.getNWins(),
                    bot.getNLosses(),
                    bot.getNDraws()
            );
            return new ApiResponse<>(200, "Bot found", botResponseDTO);
        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "Bot not found");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }
    public ApiResponse<BotResponseDTO> updateBot(int botId, BotDTO request) {
        try {
            // Buscar el bot existente
            BotDomain existingBot = botPort.findById(botId)
                    .orElseThrow(() -> new IllegalArgumentException("Bot not found with ID: " + botId));

            // Actualizar los campos editables
            existingBot.setIdeologia(request.getName());
            existingBot.setDescription(request.getDescription());
            existingBot.setUrlImagen(request.getUrlImagen());
            existingBot.setUrl(request.getEndpoint());
            existingBot.setUserId(request.getUserId());

            // Guardar el bot actualizado
            botPort.save(existingBot);

            // Crear y devolver el BotResponseDTO
            BotResponseDTO responseDTO = new BotResponseDTO(
                    existingBot.getId(),
                    existingBot.getIdeologia(),
                    existingBot.getDescription(),
                    existingBot.getUrlImagen(),
                    existingBot.getNWins(),
                    existingBot.getNLosses(),
                    existingBot.getNDraws()
            );

            return new ApiResponse<>(200, "Bot updated", responseDTO);

        } catch (IllegalArgumentException e) {
            return new ApiResponse<>(404, "Bot not found with the provided ID");
        } catch (ResponseStatusException e) {
            return new ApiResponse<>(500, "Internal Server Error");
        } catch (Exception e) {
            return new ApiResponse<>(500, "Internal Server Error");
        }
    }

}