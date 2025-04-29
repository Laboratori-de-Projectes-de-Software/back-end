package com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers;

import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MensajeEntity;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.MessageResponseDTO;
import org.springframework.stereotype.Component;


@Component
public class MensajeMapper {

    public MessageResponseDTO toMessageResponseDTO(MensajeEntity mensajeEntity) {
        return new MessageResponseDTO(
                mensajeEntity.getContenido(),
                mensajeEntity.getBot().getId(),
                mensajeEntity.getHora().toString() // o .format(...) si quieres formatearlo
        );
    }
}
