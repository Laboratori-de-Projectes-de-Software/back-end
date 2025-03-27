package org.example.backend.databaseapi.application.usecase.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.resultado.CrearResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.CreateResultadoPort;
import org.example.backend.databaseapi.domain.resultado.Resultado;
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
