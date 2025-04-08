package com.example.back_end_eing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactivar CSRF solo en desarrollo
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permitir acceso sin autenticación
                        .anyRequest().authenticated() // El resto de endpoints requieren login
                );

        return http.build();
    }
}