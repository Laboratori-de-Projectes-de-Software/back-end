package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.application.ports.BotRepository;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.BotResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.MatchResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;
import org.springframework.stereotype.Component;

@Component
public class EnfrentamientoMapper {

    private final BotMapper botMapper;

    public EnfrentamientoMapper(BotMapper botMapper) {
        this.botMapper = botMapper;
    }

    public MatchResponseDTO toMatchResponseDTO(EnfrentamientoEntity enfrentamientoEntity) {

        MatchResponseDTO match = new MatchResponseDTO();
        match.setMatchId(enfrentamientoEntity.getId());
        match.setState(enfrentamientoEntity.getEstado());

        if (enfrentamientoEntity.getPuntuacionLocal() > enfrentamientoEntity.getPuntuacionVisitante()) {
            match.setResult(1);

        } else if (enfrentamientoEntity.getPuntuacionLocal() < enfrentamientoEntity.getPuntuacionVisitante()) {
            match.setResult(2);
        } else {
            match.setResult(0);
        }

        // Convertir BotEntity a BotResponseDTO
        BotEntity localBot = enfrentamientoEntity.getBotLocal();
        BotEntity visitanteBot = enfrentamientoEntity.getBotVisitante();

        // Crear objetos Bot desde las entidades y convertirlos a DTOs
        Bot localBotDomain = botMapper.toDomain(localBot);
        Bot visitanteBotDomain = botMapper.toDomain(visitanteBot);

        BotResponseDTO[] fighters = {
                BotMapper.toBotResponseDto(localBotDomain),
                BotMapper.toBotResponseDto(visitanteBotDomain)
        };
        match.setFighters(fighters);

        // Ronda
        match.setRoundNumber(enfrentamientoEntity.getRonda());
        return match;
    }
}
