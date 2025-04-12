package com.example.back_end_eing.services;

import com.example.back_end_eing.dto.PerfilUsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    public PerfilUsuarioDTO getDatosPerfil(int userId);
}
