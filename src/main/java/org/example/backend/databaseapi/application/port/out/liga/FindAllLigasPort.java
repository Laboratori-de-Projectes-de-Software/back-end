package org.example.backend.databaseapi.application.port.out.liga;

import org.example.backend.databaseapi.domain.liga.Liga;

import java.util.List;

public interface FindAllLigasPort {

    List<Liga> findAllLigas();

}
