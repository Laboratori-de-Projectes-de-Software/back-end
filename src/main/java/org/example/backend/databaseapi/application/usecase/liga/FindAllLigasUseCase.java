package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.BuscarAllLigasPort;
import org.example.backend.databaseapi.application.port.out.liga.FindAllLigasPort;
import org.example.backend.databaseapi.domain.Liga;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindAllLigasUseCase implements BuscarAllLigasPort {

    private final FindAllLigasPort findAllLigasPort;

    @Override
    public List<Liga> buscarAllLigas() {
        return findAllLigasPort.findAllLigas();
    }
}
