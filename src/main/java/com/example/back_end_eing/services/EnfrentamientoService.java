package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.EnfrentamientoDTO;
import com.example.back_end_eing.dto.MatchResponseDTO;
import com.example.back_end_eing.dto.MessageResponseDTO;
import com.example.back_end_eing.models.Enfrentamiento;

import java.util.List;

public interface EnfrentamientoService {
    //public Enfrentamiento obtenerEnfrentamiento(Long id);
    public List<MessageResponseDTO> obtenerConversacion(Long id);
    //public Enfrentamiento obtenerEnfrentamientoConParticipaciones(Long id);
    public void generarEnfrentamientos(Long id);
    List<MatchResponseDTO> getMatchesByLeagueId(Long leagueId);
    MatchResponseDTO getMatchById(Long matchId);
}