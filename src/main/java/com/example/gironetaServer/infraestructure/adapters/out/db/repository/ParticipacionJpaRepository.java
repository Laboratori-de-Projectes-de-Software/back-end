package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipacionJpaRepository extends JpaRepository<ParticipacionEntity, Long> {
    List<ParticipacionEntity> findByLeagueId(Long leagueId); //Take a look about creating a Participation class instead using an Entity class
}