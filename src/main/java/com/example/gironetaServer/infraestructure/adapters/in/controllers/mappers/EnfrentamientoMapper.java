package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.MatchResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;
import org.springframework.stereotype.Component;

@Component
public class EnfrentamientoMapper {

    public MatchResponseDTO toMatchResponseDTO(EnfrentamientoEntity enfrentamientoEntity) {

        MatchResponseDTO match = new MatchResponseDTO();
        match.setId(enfrentamientoEntity.getId());
        match.setState(enfrentamientoEntity.getEstado());

        if (enfrentamientoEntity.getPuntuacionLocal() > enfrentamientoEntity.getPuntuacionVisitante()) {
            match.setResult(0);

        } else if (enfrentamientoEntity.getPuntuacionLocal() < enfrentamientoEntity.getPuntuacionVisitante()) {
            match.setResult(1);
        } else {
            match.setResult(-1);
        }

        // private String[] fighters;
        String[] fighters = { enfrentamientoEntity.getBotLocal().getName(),
                enfrentamientoEntity.getBotVisitante().getName()
        };
        match.setFighters(fighters);

        // Ronda
        match.setRoundNumber(enfrentamientoEntity.getRonda());
        return match;
    }
}
