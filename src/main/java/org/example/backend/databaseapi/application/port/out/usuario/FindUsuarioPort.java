package org.example.backend.databaseapi.application.port.out.usuario;

import org.example.backend.databaseapi.domain.Usuario;

import java.util.Optional;

public interface FindUsuarioPort {

    Optional<Usuario> findUsuario(Integer id_usuario);

    Optional<Usuario> findUsuario(String email);
}
