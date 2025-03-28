package com.adondeband.back_end_adonde_band.dominio.usuario;

import java.util.List;

public interface UsuarioPort {
    Usuario save(Usuario usuario);

    List<Usuario> findByNombre(String s);
}
