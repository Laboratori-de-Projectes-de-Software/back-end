package org.example.backend.databaseapi.application.usecase.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.resultado.BuscarResultadosPartidaPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindPartidaResultadoPort;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindPartidaResultadosUseCase implements BuscarResultadosPartidaPort {

    private final FindPartidaResultadoPort findPartidaResultadoPort;

    @Override
    public List<Resultado> buscarResultadosPartida(Integer partidaId) {
        return findPartidaResultadoPort.findPartidaResultado(partidaId);
    }

}
