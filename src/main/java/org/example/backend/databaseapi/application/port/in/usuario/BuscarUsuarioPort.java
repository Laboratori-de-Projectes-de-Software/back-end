package org.example.backend.databaseapi.application.port.in.usuario;

import org.example.backend.databaseapi.domain.usuario.Usuario;

public interface BuscarUsuarioPort {

    Usuario buscarUsuario(Integer userId);

    Usuario buscarUsuario(String email);
}
