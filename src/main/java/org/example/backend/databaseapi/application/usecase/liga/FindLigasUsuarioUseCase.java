package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.in.liga.BuscarLigaUsuarioPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaUsuarioPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindLigasUsuarioUseCase implements BuscarLigaUsuarioPort {

    private final FindLigaUsuarioPort findLigaUsuarioPort;

    @Override
    public List<Liga> buscarLigasUsuario(Integer userId) {
        return findLigaUsuarioPort.findLigasUsuario(userId);
    }
}
