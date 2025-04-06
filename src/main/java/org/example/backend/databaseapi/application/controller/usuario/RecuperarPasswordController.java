package org.example.backend.databaseapi.application.controller.usuario;

import org.example.backend.databaseapi.domain.usuario.Email;
import org.example.backend.databaseapi.domain.usuario.TokenAndPassword;
import org.example.backend.databaseapi.application.port.in.usuario.RecuperarPasswordPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario/password")
public class RecuperarPasswordController {
    
    private final RecuperarPasswordPort recuperarPasswordPort;
    
    @Autowired
    public RecuperarPasswordController(RecuperarPasswordPort recuperarPasswordPort) {
        this.recuperarPasswordPort = recuperarPasswordPort;
    }
    
    @PostMapping("/recuperar")
    public ResponseEntity<String> solicitarRecuperacion(@RequestBody Email email) {
        boolean existeUsuario = recuperarPasswordPort.procesarSolicitudRecuperacion(email.value());
        
        if (!existeUsuario) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Email no registrado");
        }

        return ResponseEntity.ok("Se ha enviado un enlace de recuperación a tu correo");
    }
    
    @GetMapping("/validar-token/{token}")
    public ResponseEntity<String> validarToken(@PathVariable String token) {
        boolean tokenValido = recuperarPasswordPort.validarTokenRecuperacion(token);
        
        if (!tokenValido) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El enlace de recuperación es inválido o ha expirado");
        }
        
        return ResponseEntity.ok("Token válido");
    }
    
    @PostMapping("/cambiar")
    public ResponseEntity<String> cambiarPassword(@RequestBody TokenAndPassword dto) {
        boolean cambiado = recuperarPasswordPort.cambiarPassword(
                dto.getToken(), dto.getNuevaPassword());
        
        if (!cambiado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se pudo cambiar la contraseña. El enlace podría ser inválido o haber expirado");
        }
        
        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }
}
