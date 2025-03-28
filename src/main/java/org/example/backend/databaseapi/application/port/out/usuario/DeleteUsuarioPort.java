package org.example.backend.databaseapi.application.port.out.usuario;

import org.example.backend.databaseapi.domain.Usuario;

public interface DeleteUsuarioPort {

    Usuario deleteUsuario(int id_usuario);

}
