package org.example.backend.databaseapi.application.usecase.resultado;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.resultado.BuscarResultadosLiga;
import org.example.backend.databaseapi.application.port.out.resultado.FindLigaResultadoPort;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindLigaResultadoUseCase implements BuscarResultadosLiga {

    private final FindLigaResultadoPort findLigaResultadoPort;

    @Override
    public List<Resultado> buscarResultadosLiga(Integer idLiga) {
        return findLigaResultadoPort.findResultadoLiga(idLiga);
    }
}
