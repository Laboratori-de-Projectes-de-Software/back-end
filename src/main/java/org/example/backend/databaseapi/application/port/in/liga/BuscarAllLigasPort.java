package org.example.backend.databaseapi.application.port.in.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

import java.util.List;

public interface BuscarAllLigasPort {

    List<Liga> buscarAllLigas();
}
