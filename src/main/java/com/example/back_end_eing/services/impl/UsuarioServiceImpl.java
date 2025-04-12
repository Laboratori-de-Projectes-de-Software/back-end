package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.PerfilUsuarioDTO;
import com.example.back_end_eing.exceptions.UserNotFoundException;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public PerfilUsuarioDTO getDatosPerfil(int id) {

        Usuario usuario = getPerfilByUserId(id);
        String img = !Objects.equals(usuario.getFoto(), "")
                ? usuario.getFoto()
                : null;


        return new PerfilUsuarioDTO(
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                img,
                usuario.getBots().size(),
                usuario.getLigasUsuario().size()
        );
    }


    private Usuario getPerfilByUserId(int id) {
        return usuarioRepository.findById((long) id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }
}
