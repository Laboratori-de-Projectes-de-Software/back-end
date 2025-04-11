package com.adondeband.back_end_adonde_band.dominio.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioImpl implements UsuarioService {
    private final UsuarioPort usuarioPort;

    public UsuarioImpl(UsuarioPort usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioPort.findByNombre(usuario.getNombre()).isEmpty()) {
            return usuarioPort.save(usuario);
        } else {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
    }

    @Override
    public Usuario obtenerUsuarioPorNombre(String nombre) {
        return usuarioPort.findByNombre(nombre).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }


}
