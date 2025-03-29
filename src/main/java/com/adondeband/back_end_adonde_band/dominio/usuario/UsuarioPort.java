package com.adondeband.back_end_adonde_band.dominio.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPort {
    Usuario save(Usuario usuario);

    Optional<Usuario> findByNombre(String correo);


}
