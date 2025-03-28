package org.example.backend.databaseapi.application.port.out.usuario;

import org.example.backend.databaseapi.domain.Usuario;

import java.util.Optional;

public interface CreateUsuarioPort {

    Optional<Usuario> createUsuario(Usuario usuario);

}
