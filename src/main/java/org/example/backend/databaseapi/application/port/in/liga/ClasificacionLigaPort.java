package org.example.backend.databaseapi.application.port.in.liga;

import org.example.backend.databaseapi.application.dto.resultado.ParticipationDTOResponse;

import java.util.List;

public interface ClasificacionLigaPort {

    List<ParticipationDTOResponse> getClasificacionesLiga(Integer ligaId);
}
