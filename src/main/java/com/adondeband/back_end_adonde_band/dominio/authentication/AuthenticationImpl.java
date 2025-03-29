package com.adondeband.back_end_adonde_band.dominio.authentication;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationImpl implements AuthenticationService {
    private final UsuarioPort usuarioPort;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationImpl(
            UsuarioPort usuarioPort,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.usuarioPort = usuarioPort;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario signup(Usuario input) {
        Usuario user = new Usuario()
                .setNombre(input.getNombre())
                .setCorreo(input.getCorreo())
                .setContrasena(passwordEncoder.encode(input.getContrasena()));

        return usuarioPort.save(user);
    }

    public Usuario authenticate(Usuario input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getNombre().value(),
                        input.getContrasena()
                )
        );

        return usuarioPort.findByNombre(input.getNombre().value())
                .orElseThrow();
    }
}
