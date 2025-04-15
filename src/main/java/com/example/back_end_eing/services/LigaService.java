package com.example.back_end_eing.services;

import java.util.List;

import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.dto.ParticipationResponseDTO;


public interface LigaService {


    //Devolver la liga ordenada por clasificación
    List<ParticipationResponseDTO> getClasificacion(Long ligaId);

    void LigaRegistro(LeagueDTO ligadto);

    List<LeagueResponseDTO> obtenerLigas();

    List<LeagueResponseDTO> obtenerLigasByUserId(Long userId);

    LeagueResponseDTO getLiga(Long id);

    Long getOwnerByLeagueId(Long id);

    void deleteLiga(Long id);

    void registerBotToLeague(Long botId, Long leagueId);

    void actualizarLiga(LeagueDTO ligadto, Long leagueId);


}
