package com.example.demo.application.service;

import com.example.demo.application.port.in.BotUseCase;
import com.example.demo.application.port.in.BotUseCase;
import com.example.demo.application.port.out.BotRepository;
import com.example.demo.domain.model.Bot;
import com.example.demo.domain.model.User;
import com.example.demo.dtos.BotDTO;
import com.example.demo.dtos.BotSummaryResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de los casos de uso para la gestión de ligas.
 */
@Service
public class BotService implements BotUseCase {

    private final BotRepository botRepository;

    public BotService(BotRepository botRepository) {
        this.botRepository = botRepository;
    }

    @Override
    public BotDTO createBot(BotDTO botDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        Bot bot = new Bot(botDTO.getId(), botDTO.getName(), botDTO.getDescription(), botDTO.getUrlImagen(), botDTO.getEndpoint(), userId);
        Bot savedBot = botRepository.save(bot);
        return toDTO(savedBot);
    }

    @Override
    public BotDTO getBot(Integer botId) {
        Bot bot = botRepository.findById(botId);
        return toDTO(bot);
    }

    @Override
    public List<BotSummaryResponseDTO> listBots() {
        return botRepository.findAll().stream()
                .map(this::toDTOSum)
                .collect(Collectors.toList());
    }

    @Override
    public List<BotSummaryResponseDTO> listBotsByOwner(Integer ownerId) {
        return botRepository.findByOwnerId(ownerId).stream()
                .map(this::toDTOSum)
                .collect(Collectors.toList());
    }

    private BotDTO toDTO(Bot bot) {
        BotDTO dto = new BotDTO();
        dto.setId(bot.getId());
        dto.setName(bot.getName());
        dto.setDescription(bot.getDescription());
        dto.setUrlImagen(bot.getUrlImagen());
        dto.setEndpoint(bot.getEndpoint());
        return dto;
    }

    private BotSummaryResponseDTO toDTOSum(Bot bot) {
        BotSummaryResponseDTO dto = new BotSummaryResponseDTO();
        dto.setId(bot.getId());
        dto.setName(bot.getName());
        dto.setDescription(bot.getDescription());
        return dto;
    }
}
