package com.example.gironetaServer.application.ports;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;

import java.util.List;
import java.util.Optional;

public interface ParticipacionRepository {
    List<ParticipationResponseDto> findByLeagueId(Long leagueId); //Take a look about creating a Participation class instead using an Entity class
}
