package org.example.backend.databaseapi.application.port.in.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

import java.util.List;

public interface BuscarLigaUsuarioPort {

    List<Liga> buscarLigasUsuario(Integer userId);

}
