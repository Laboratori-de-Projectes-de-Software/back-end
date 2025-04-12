package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.AnadirBotLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.AddBotLigaPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnadirBotLigaUseCase implements AnadirBotLigaPort {

    private final AddBotLigaPort addBotLigaPort;

    @Override
    public void anadirBotLiga(Integer botId, Integer ligaId) {
        addBotLigaPort.addBotLiga(botId,ligaId);
    }
}
