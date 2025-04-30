package org.example.backend.databaseapi.application.usecase.usuario;

import org.example.backend.databaseapi.application.port.in.usuario.RecuperarPasswordPort;
import org.example.backend.databaseapi.application.port.out.usuario.EmailPort;
import org.example.backend.databaseapi.application.port.out.usuario.FindUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.UpdateUsuarioPort;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Lazy
public class RecuperarPasswordUseCase implements RecuperarPasswordPort {


    private final FindUsuarioPort usuarioPort;
    private final UpdateUsuarioPort updateUsuarioPort;
    private final EmailPort emailPort;
    
    @Autowired
    public RecuperarPasswordUseCase(FindUsuarioPort usuarioPort, UpdateUsuarioPort updateUsuarioPort, EmailPort emailPort) {
        this.usuarioPort = usuarioPort;
        this.updateUsuarioPort = updateUsuarioPort;
        this.emailPort = emailPort;
    }
    
    @Override
    @Transactional
    public boolean procesarSolicitudRecuperacion(String email) {
        Optional<Usuario> usuarioOpt = usuarioPort.findUsuario(email);
        
        if (usuarioOpt.isEmpty()) {
            // El email no existe en la base de datos
            return false;
        }
        
        Usuario usuario = usuarioOpt.get();
        
        // Generar token único y establecer fecha de expiración (1 hora)
        String resetToken = UUID.randomUUID().toString();
        usuario.setResetPasswordToken(resetToken);
        usuario.setResetPasswordExpires(LocalDateTime.now().plusHours(1));
        
        // Guardar el usuario con el nuevo token
        updateUsuarioPort.updateUsuario(usuario, usuario.getUserId().value());
        
        // Enviar correo con el token
        emailPort.enviarCorreoRecuperacion(email, resetToken);
        
        return true;
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean validarTokenRecuperacion(String token) {
        Optional<Usuario> usuarioOpt = usuarioPort.findUsuarioByResetPasswordToken(token);
        
        if (usuarioOpt.isEmpty()) {
            return false;
        }
        
        Usuario usuario = usuarioOpt.get();
        LocalDateTime ahora = LocalDateTime.now();
        
        // Verificar que el token no ha expirado
        return usuario.getResetPasswordExpires() != null && 
               usuario.getResetPasswordExpires().isAfter(ahora);
    }
    
    @Override
    @Transactional
    public boolean cambiarPassword(String token, String nuevaPassword) {
        Optional<Usuario> usuarioOpt = usuarioPort.findUsuarioByResetPasswordToken(token);
        
        if (usuarioOpt.isEmpty()) {
            return false;
        }
        
        Usuario usuario = usuarioOpt.get();
        LocalDateTime ahora = LocalDateTime.now();
        
        // Verificar que el token no ha expirado
        if (usuario.getResetPasswordExpires() == null || 
            usuario.getResetPasswordExpires().isBefore(ahora)) {
            return false;
        }
        
        // Actualizar contraseña y limpiar token
        usuario.setPassword(nuevaPassword); // Se asume que hay un evento que encripta la contraseña
        usuario.setResetPasswordToken(null);
        usuario.setResetPasswordExpires(null);

        updateUsuarioPort.updateUsuario(usuario, usuario.getUserId().value());
        
        return true;
    }
}
