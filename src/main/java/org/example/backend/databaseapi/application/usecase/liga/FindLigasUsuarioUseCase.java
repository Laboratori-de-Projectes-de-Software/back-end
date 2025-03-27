package org.example.backend.databaseapi.application.usecase.liga;

import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaUsuarioPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindLigasUsuarioUseCase {

    private final FindLigaUsuarioPort findLigaUsuarioPort;

    public List<Liga> execute(int id_user){
        return findLigaUsuarioPort.findLigasUsuario(id_user);
    }

}
