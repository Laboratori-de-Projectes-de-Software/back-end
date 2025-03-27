package org.example.backend.databaseapi.application.usecase.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.resultado.BuscarResultadosBotPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindBotResultadoPort;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindBotResultadosUseCase implements BuscarResultadosBotPort {

    private final FindBotResultadoPort findBotResultadoPort;

    @Override
    public List<Resultado> buscarResultadosBot(Integer botId) {
        return findBotResultadoPort.findBotResultados(botId);
    }
}
