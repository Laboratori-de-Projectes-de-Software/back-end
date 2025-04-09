package com.example.back_end_eing.services;

import java.util.List;

import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.models.Clasificacion;

public interface LigaService {

    //Actualizar la clasificación según un resultado
    public void LigaActualización(Long Liga, Long local, Long visitante, String resultado);

    //Devolver la liga ordenada por clasificación
    public List<Clasificacion> LigaClasificacion(Long liga);

    public void LigaRegistro(LeagueDTO ligadto);

}
