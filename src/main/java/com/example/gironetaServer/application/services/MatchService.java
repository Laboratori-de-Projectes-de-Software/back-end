package com.example.gironetaServer.application.services;


import com.example.gironetaServer.application.ports.MensajeRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MessageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class MatchService {

    private final MensajeRepository mensajeRepository;


    public List<MessageResponseDTO> getMessagesFromEnfrentamiento(Long matchId) {
        return mensajeRepository.findByEnfrentamientoId(matchId);
    }
}
