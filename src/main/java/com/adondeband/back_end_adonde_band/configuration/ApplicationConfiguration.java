package com.adondeband.back_end_adonde_band.configuration;

import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@Configuration
public class ApplicationConfiguration {
    private final UsuarioPort usuarioPort;

    public ApplicationConfiguration(UsuarioPort usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> usuarioPort.findByNombre(username)
                .map(usuario -> new org.springframework.security.core.userdetails.User(
                        usuario.getNombre().value(),
                        usuario.getContrasena(),
                        new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
