package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.EnfrentamientoDTO;
import com.example.back_end_eing.models.Enfrentamiento;

public interface EnfrentamientoService {
    public Enfrentamiento obtenerEnfrentamiento(Long id);
    public EnfrentamientoDTO obtenerConversacion(Long id);
    public void generarEnfrentamientos(Long id);
}
