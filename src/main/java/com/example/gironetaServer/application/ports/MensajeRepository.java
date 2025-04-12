package com.example.gironetaServer.application.ports;


import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MessageResponseDTO;

import java.util.List;

public interface MensajeRepository {
    List<MessageResponseDTO> findByEnfrentamientoId(Long enfrentamientoId);
}
