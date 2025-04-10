package com.debateia.application.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

        final String jwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(jwt);
        System.out.println("USER EMAIL: "+userEmail);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userEmail == null || authentication != null) {
            filterChain.doFilter(request, response);
            return;
        }

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        /*
         * final boolean isTokenExpiredOrRevoked = tokenRepository.findByToken(jwt)
         * .map(token -> !token.getIsExpired() && !token.getIsRevoked())
         * .orElse(false);
         */

        /*
         * if (isTokenExpiredOrRevoked) {
         * final Optional<UserEntity> userEntity =
         * userRepository.findByEmail(userEmail);
         * 
         * if (userEntity.isPresent()) {
         * final boolean isTokenValid = jwtService.isTokenValid(jwt, userEntity.get());
         * 
         * if (isTokenValid) {
         * UsernamePasswordAuthenticationToken authToken = new
         * UsernamePasswordAuthenticationToken(
         * userDetails,
         * null,
         * userDetails.getAuthorities());
         * authToken.setDetails(
         * new WebAuthenticationDetailsSource().buildDetails(request));
         * SecurityContextHolder.getContext().setAuthentication(authToken);
         * }
         * }
         * }
         */

        filterChain.doFilter(request, response);
    }
}
