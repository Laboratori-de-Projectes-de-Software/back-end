package org.example.backend.databaseapi.application.port.out.usuario;

import org.example.backend.databaseapi.domain.Usuario;

public interface UpdateUsuarioPort {

    Usuario updateUsuario(Usuario usuario, Integer id);

}
