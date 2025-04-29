package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.EnfrentamientoEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MensajeEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MessageResponseDTO;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.ParticipacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeJpaRepository extends JpaRepository<MensajeEntity, Long> {
    List<MensajeEntity> findByEnfrentamientoId(Long enfrentamientoId);
}