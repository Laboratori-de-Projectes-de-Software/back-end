package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EntityScan(basePackages = "com.example.demo")  // Asegura que Spring pueda detectar las entidades JPA
@EnableJpaRepositories(basePackages = "com.example.demo")  // Habilita los repositorios JPA
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

   
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())  // Desactiva CSRF para simplificar
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll()  // Permite acceso sin autenticación a /auth/**
            .anyRequest().authenticated()  // Exige autenticación para el resto
        )
        .formLogin(form -> form.disable())  // Desactiva el login por formulario
        .httpBasic(httpBasic -> httpBasic.disable());  // Desactiva la autenticación básica HTTP

    return http.build();  // Devuelve el objeto configurado
}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usamos BCrypt para la codificación de contraseñas
    }
}
