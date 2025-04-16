package jaumesitos.backend.demo.infrastructure.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private String version = "/api/v0";
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((req, res, excep) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado")
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(version+"/auth/login", version+"/auth/register", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, version+"/bot/**").authenticated()
                        .requestMatchers(HttpMethod.POST, version+"/league/**").authenticated()  // POST requires authentication
                        .requestMatchers(HttpMethod.PUT, version+"/league/**").authenticated()   // PUT requires authentication
                        .requestMatchers(HttpMethod.GET, "/api/v0/league/**").authenticated()  // GET requires authentication
                        .requestMatchers(HttpMethod.DELETE, version+"/league/**").authenticated()   // DELETE requires authentication
                        .requestMatchers(HttpMethod.GET, version+"/match/**").permitAll()  // POST requires authentication


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
