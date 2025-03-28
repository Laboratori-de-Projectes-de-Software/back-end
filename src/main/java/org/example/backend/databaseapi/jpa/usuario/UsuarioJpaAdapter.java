package org.example.backend.databaseapi.jpa.usuario;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.usuario.CreateUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.DeleteUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.FindUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.UpdateUsuarioPort;
import org.example.backend.databaseapi.application.service.PasswordService;
import org.example.backend.databaseapi.domain.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UsuarioJpaAdapter implements CreateUsuarioPort, FindUsuarioPort, DeleteUsuarioPort, UpdateUsuarioPort {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioJpaMapper usuarioJpaMapper;
    private final PasswordService passwordService;

    @Override
    @Transactional
    public Optional<Usuario> createUsuario(Usuario usuario) {
        if(!usuarioJpaRepository.existsByEmail(usuario.getEmail())){
            // Encriptar la contraseña antes de guardar
            UsuarioJpaEntity entity = usuarioJpaMapper.toEntity(usuario);
            entity.setPassword(passwordService.encryptPassword(usuario.getPassword()));
            return Optional.of(usuarioJpaMapper.toDomain(usuarioJpaRepository.save(entity)));
        }
        return Optional.empty();
    }

    @Override
    public Usuario deleteUsuario(int id_usuario) {
        usuarioJpaRepository.deleteById(id_usuario);
        return null;
    }

    @Override
    public Optional<Usuario> findUsuario(Integer id_usuario) {
        return usuarioJpaRepository.findById(id_usuario).map(usuarioJpaMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findUsuario(String email) {
        return usuarioJpaRepository.findByEmail(email).map(usuarioJpaMapper::toDomain);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario, Integer id) {
        return usuarioJpaRepository.findById(id)
                .map(user -> {
                    user.setNombre(usuario.getNombre());
                    user.setEmail(usuario.getEmail());
                    user.setImagen(usuario.getImagen());
                    // Solo actualizar contraseña si se proporciona una nueva
                    if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                        user.setPassword(passwordService.encryptPassword(usuario.getPassword()));
                    }
                    return usuarioJpaMapper.toDomain(usuarioJpaRepository.save(user));
                })
                .orElseGet(() -> {
                    UsuarioJpaEntity entity = usuarioJpaMapper.toEntity(usuario);
                    entity.setPassword(passwordService.encryptPassword(usuario.getPassword()));
                    return usuarioJpaMapper.toDomain(usuarioJpaRepository.save(entity));
                });
    }
}