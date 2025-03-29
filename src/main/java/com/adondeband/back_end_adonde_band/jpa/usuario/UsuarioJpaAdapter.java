package com.adondeband.back_end_adonde_band.jpa.usuario;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioJpaAdapter implements UsuarioPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    private final UsuarioJpaMapper usuarioJpaMapper;

    public UsuarioJpaAdapter(final UsuarioJpaRepository usuarioJpaRepository, final UsuarioJpaMapper usuarioJpaMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioJpaMapper = usuarioJpaMapper;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioJpaMapper.toDomain(
                usuarioJpaRepository.save(
                        usuarioJpaMapper.toEntity(usuario)));
    }

    @Override
    public Optional<Usuario> findByNombre(String s) {
        return usuarioJpaRepository.findByNombre(s)
                .stream()
                .map(usuarioJpaMapper::toDomain)
                .findFirst();
    }
}
