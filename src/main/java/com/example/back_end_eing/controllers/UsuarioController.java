package com.example.back_end_eing.controllers;

import com.example.back_end_eing.dto.PerfilUsuarioDTO;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v0/perfil")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{userId}")
    public PerfilUsuarioDTO getPerfil(@PathVariable Long userId) {
        return usuarioService.getDatosPerfil(userId);
    }
}
