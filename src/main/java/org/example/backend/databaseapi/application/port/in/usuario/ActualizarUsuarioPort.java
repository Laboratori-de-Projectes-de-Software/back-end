package org.example.backend.databaseapi.application.port.in.usuario;

import org.example.backend.databaseapi.domain.usuario.Usuario;

public interface ActualizarUsuarioPort {

    Usuario actualizarUsuario(Usuario user,Integer id);
}
