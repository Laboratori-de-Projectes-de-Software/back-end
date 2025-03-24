package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.constants.EstadoLigaConstants;
import com.example.back_end_eing.exceptions.IncorrectNumBotsException;
import org.springframework.stereotype.Service;

import com.example.back_end_eing.exceptions.UserNotFoundException;
import com.example.back_end_eing.models.Liga;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.repositories.LigaRepository;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.services.LigaService;

@Service
public class LigaServiceImpl implements LigaService {
    private LigaRepository ligaRepository;
    private UsuarioRepository usuarioRepository;
    private Liga liga;
    private Usuario usuario;

    public void LigaRegistro(String nombreLiga, Integer numJornadas, Integer numBots, String estado, Integer jornadaActual, Long id){
        // solo un número de bots par, controlar en el front-end números > 0
        if (numBots % 2 != 0) {
            throw new IncorrectNumBotsException(numBots);
        }
        usuario = getUsuario(id);
        // establecer estado = ABIERTA por defecto si el usuario no lo especifica
        if (estado == null || estado.isEmpty()) {
            estado = EstadoLigaConstants.ABIERTA;
        }
        liga = new Liga(nombreLiga, numJornadas, numBots, estado, jornadaActual, usuario);
        ligaRepository.save(liga);
    }

    private Usuario getUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}