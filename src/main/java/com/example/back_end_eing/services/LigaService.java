package com.example.back_end_eing.services;

import java.util.List;

import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.ParticipationResponseDTO;
import com.example.back_end_eing.models.Clasificacion;
import com.example.back_end_eing.models.Liga;

public interface LigaService {

    //Actualizar la clasificación según un resultado
    void LigaActualizacion(Long Liga, Long local, Long visitante, String resultado);

    //Devolver la liga ordenada por clasificación
    List<ParticipationResponseDTO> getClasificacion(Long ligaId);

    public void LigaRegistro(LeagueDTO ligadto);

    public List<LeagueResponseDTO> obtenerLigas();

    public List<Liga> obtenerLigasUser(Long id);

    LeagueResponseDTO getLiga(Long id);

    void deleteLiga(Long id);
}
