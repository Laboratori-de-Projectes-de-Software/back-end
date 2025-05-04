package com.adondeband.back_end_adonde_band.dominio.conversacion;

import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ConversacionImpl implements ConversacionService {

    private final ConversacionPort conversacionPort;

    public ConversacionImpl(ConversacionPort conversacionPort) {
        this.conversacionPort = conversacionPort;
    }

    @Override
    public Conversacion crearConversacion(Conversacion conversacion) {
        return conversacionPort.save(conversacion);
    }

    @Override
    public Mensajes obtenerMensajes(String rutaFichero) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Para manejar LocalDateTime

            // Leer y mapear el JSON al objeto Java
            return objectMapper.readValue(new File(rutaFichero), Mensajes.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
