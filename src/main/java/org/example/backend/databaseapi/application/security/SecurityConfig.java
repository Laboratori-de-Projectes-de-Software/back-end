package org.example.backend.databaseapi.application.security;

import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Endpoints que necesitan sesión iniciada para acceder
                        .requestMatchers(HttpMethod.POST, "/bot").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/bot/{botId}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/league").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/league/{leagueId}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/league/{leagueId}/bot").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/league/{leagueId}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/league/{leagueId}/start").authenticated()
                        // El resto no necesitan autenticación
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}