package org.example.backend.databaseapi.application.service;

import org.example.backend.databaseapi.application.port.in.usuario.BuscarUsuarioPort;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final BuscarUsuarioPort buscarUsuarioPort;

    public CustomUserDetailsService(BuscarUsuarioPort buscarUsuarioPort) {
        this.buscarUsuarioPort = buscarUsuarioPort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = buscarUsuarioPort.buscarUsuario(username);
        return User.builder()
                .username(usuario.getEmail().value()) // "username" será email en nuestro caso, no confundir para cuando se hace loadUserByUsername()
                .password(usuario.getPassword())
                .build();
    }
}