package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.MensajeRepository;
import com.example.gironetaServer.application.ports.ParticipacionRepository;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.ParticipationResponseDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.MensajeMapper;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.ParticipacionMapper;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MessageResponseDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MensajeDatabaseRepository implements MensajeRepository {

    private final MensajeJpaRepository mensajeJpaRepository;
    private final MensajeMapper mensajeMapper;

    public MensajeDatabaseRepository(MensajeJpaRepository mensajeJpaRepository, MensajeMapper mensajeMapper) {
        this.mensajeJpaRepository = mensajeJpaRepository;
        this.mensajeMapper = mensajeMapper;
    }



    @Override
    @Transactional
    public List<MessageResponseDTO> findByEnfrentamientoId(Long enfrentamientoId) {

        return mensajeJpaRepository.findByEnfrentamientoId(enfrentamientoId)
                .stream()
                .map( mensajeEntity -> mensajeMapper.toMessageResponseDTO(mensajeEntity))
                .collect(Collectors.toList());
    }
}