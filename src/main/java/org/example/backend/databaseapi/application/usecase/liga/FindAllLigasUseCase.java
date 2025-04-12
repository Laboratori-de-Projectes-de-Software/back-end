package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.BuscarAllLigasPort;
import org.example.backend.databaseapi.application.port.out.liga.FindAllLigasPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllLigasUseCase implements BuscarAllLigasPort {

    private final FindAllLigasPort findAllLigasPort;

    @Override
    public List<Liga> buscarAllLigas() {
        return findAllLigasPort.findAllLigas();
    }
}
