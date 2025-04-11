package com.debateia.application.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.debateia.application.ports.out.persistence.TokenRepository;
import com.debateia.application.ports.out.persistence.UserRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);  // Extraemos el token del header
        final String userEmail = jwtService.extractUsername(jwt);  // Extraemos el email del token
        System.out.println("USER EMAIL: " + userEmail);

        if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);  // Si no hay email o ya hay un usuario autenticado, continuamos con el filtro
            return;
        }

        // Validamos el token con el método que ya tienes
        boolean isTokenValid = jwtService.isTokenValid(authHeader);
        if (isTokenValid) {
            // Si el token es válido, autenticamos al usuario
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            // Configuramos la autenticación en el contexto de seguridad
            var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);  // Continuamos con el filtro
    }


}
