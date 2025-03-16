package com.adondeband.back_end_adonde_band.dominio.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioImpl implements UsuarioService {
    private UsuarioPort usuarioPort;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioPort.findByNombre(usuario.getNombre().value()).isEmpty()) {
            return usuarioPort.save(usuario);
        } else {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
    }
}
