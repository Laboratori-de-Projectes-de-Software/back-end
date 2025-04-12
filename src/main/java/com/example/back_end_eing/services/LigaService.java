package com.example.back_end_eing.services;

import java.util.List;

import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.models.Clasificacion;
import com.example.back_end_eing.models.Liga;

public interface LigaService {

    //Actualizar la clasificación según un resultado
    public void LigaActualización(Long Liga, Long local, Long visitante, String resultado);

    //Devolver la liga ordenada por clasificación
    public List<Clasificacion> LigaClasificacion(Long liga);

    public void LigaRegistro(String nombreLiga, Integer numJornadas, Integer numBots, String estado, Integer jornadaActual, Long id);

    public List<LeagueResponseDTO> obtenerLigas();

    public List<Liga> obtenerLigasUser(Long id);
}
