package com.example.back_end_eing.services;

import java.util.List;

import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.dto.ParticipationResponseDTO;
import com.example.back_end_eing.models.Liga;

public interface LigaService {


    //Devolver la liga ordenada por clasificación
    List<ParticipationResponseDTO> getClasificacion(Long ligaId);

    void LigaRegistro(LeagueDTO ligadto);

    List<LeagueResponseDTO> obtenerLigas();

    List<Liga> obtenerLigasUser(Long id);

    LeagueResponseDTO getLiga(Long id);

    void deleteLiga(Long id);
}
