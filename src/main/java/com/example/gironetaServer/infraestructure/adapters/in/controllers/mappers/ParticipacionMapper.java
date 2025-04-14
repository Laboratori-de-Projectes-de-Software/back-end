package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;
import org.springframework.stereotype.Component;


@Component
public class ParticipacionMapper {

    public ParticipationResponseDto toParticipationResponseDto(ParticipacionEntity participation) {
        return new ParticipationResponseDto(
                participation.getBot().getId(),        // ID del bot
                participation.getBot().getName(),      //Nombre del bot
                participation.getPuntuacion(),             //Puntuacion del bot
                0, // La posición la pondremos después
                participation.getNumGanados(),          // Número de victorias
                participation.getNumEmpatados(),        // Número de empates
                participation.getNumPerdidos()          // Número de derrotas
        );
    }

}
