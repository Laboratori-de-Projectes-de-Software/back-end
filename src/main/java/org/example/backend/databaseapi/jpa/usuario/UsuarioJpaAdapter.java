package org.example.backend.databaseapi.jpa.usuario;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.exception.ResourceAlreadyExistsException;
import org.example.backend.databaseapi.application.exception.ResourceNotFoundException;
import org.example.backend.databaseapi.application.exception.ValidationException;
import org.example.backend.databaseapi.application.port.out.usuario.CreateUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.DeleteUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.FindUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.UpdateUsuarioPort;
import org.example.backend.databaseapi.application.service.PasswordService;
import org.example.backend.databaseapi.domain.usuario.Email;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.stereotype.Component;

import java.util.Objects;
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
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty() ||
                usuario.getPassword() == null || usuario.getPassword().isEmpty() ||
                usuario.getEmail() == null || usuario.getEmail().value() == null || usuario.getEmail().value().isEmpty() ||
                usuario.getUserId() == null) {

            throw new ValidationException("Validation failed: " +
                    (usuario.getNombre() == null || usuario.getNombre().isEmpty() ? "Name is required. " : "") +
                    (usuario.getPassword() == null || usuario.getPassword().isEmpty() ? "Password is required. " : "") +
                    (usuario.getEmail() == null || usuario.getEmail().value() == null || usuario.getEmail().value().isEmpty() ? "Email is required. " : "") +
                    (usuario.getUserId() == null ? "User ID is required. " : "")
            );
        }

        if(!usuarioJpaRepository.existsByEmail(usuario.getEmail().value())){
            // Encriptar la contraseña antes de guardar
            UsuarioJpaEntity entity=UsuarioJpaEntity.builder()
                    .email(usuario.getEmail().value())
                    .nombre(usuario.getNombre())
                    .imagen(usuario.getImagen())
                    .password(usuario.getPassword())
                    .build();
            entity.setPassword(passwordService.encryptPassword(usuario.getPassword()));
            return Optional.of(usuarioJpaMapper.toDomain(usuarioJpaRepository.save(entity)));
        }
        return Optional.empty();
    }

    @Override
    public void deleteUsuario(int id_usuario) {
        usuarioJpaRepository.deleteById(id_usuario);
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
    public Optional<Usuario> findUsuarioByResetPasswordToken(String token) {
        return usuarioJpaRepository.findByResetPasswordToken(token).map(usuarioJpaMapper::toDomain);
    }

    @Override
    public Usuario updateUsuario(Usuario changedUser, Integer id) {
        Usuario prevUser = findUsuario(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"))    ;

        // si está intentando cambiar el email y ya está cogido
        if(!Objects.equals(prevUser.getEmail(), changedUser.getEmail()) &&
                usuarioJpaRepository.existsByEmail(changedUser.getEmail().value())){
            throw new ResourceAlreadyExistsException("Este email no está disponible");
        }

        UsuarioJpaEntity foundUser = getUser(id)
                .orElseThrow();

        UsuarioJpaEntity newUser=UsuarioJpaEntity.builder()
                .userId(id)
                .email(changedUser.getEmail().value())
                .nombre(changedUser.getNombre())
                .imagen(changedUser.getImagen())
                .resetPasswordToken(changedUser.getResetPasswordToken())
                .resetPasswordExpires(changedUser.getResetPasswordExpires())
                .build();

        if (changedUser.getPassword() != null && !changedUser.getPassword().isEmpty()) {
            newUser.setPassword(passwordService.encryptPassword(changedUser.getPassword()));
        }

        return usuarioJpaMapper.toDomain(
                usuarioJpaRepository.save(
                        usuarioJpaMapper.updateUser(newUser,foundUser)
                )
        );
    }

    public Optional<UsuarioJpaEntity> getUser(Integer id){
        if(id==null){
            return Optional.empty();
        }
        return usuarioJpaRepository.findById(id);
    }
}
