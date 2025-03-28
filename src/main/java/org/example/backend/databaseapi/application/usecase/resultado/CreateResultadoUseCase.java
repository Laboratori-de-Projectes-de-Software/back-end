package org.example.backend.databaseapi.application.usecase.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.resultado.CrearResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.CreateResultadoPort;
import org.example.backend.databaseapi.domain.Bot;
import org.example.backend.databaseapi.domain.Partida;
import org.example.backend.databaseapi.domain.Resultado;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateResultadoUseCase implements CrearResultadoPort {

    private final CreateResultadoPort createResultadoPort;

    @Override
    public Resultado crearResultado(Resultado resultado) {
        return createResultadoPort.createResultado(resultado);
    }
}
