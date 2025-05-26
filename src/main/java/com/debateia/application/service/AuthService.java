package com.debateia.application.service;

import com.debateia.application.ports.in.rest.AuthUseCase;
import com.debateia.adapter.in.rest.auth.UpdateCredRequest;
import com.debateia.adapter.in.rest.auth.UserLoginDTO;
import com.debateia.adapter.in.rest.auth.UserDTORegister;
import com.debateia.adapter.in.rest.auth.TokenData;
import com.debateia.application.ports.in.rest.JWTUseCase;
import com.debateia.application.ports.out.persistence.UserRepository;
import com.debateia.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUseCase jwtUseCase;
    private final AuthenticationManager authenticationManager;
    @Autowired
    @Value("${spring.application.security.jwt.expiration}")
    private int expiration; // Elimina "final"

    @Override
    public User register(final UserDTORegister request) {
        if (repository.existsByMail(request.mail())) { // mail existe?
            throw new DataIntegrityViolationException("Email \""+request.mail()+"\" ya existe");
        }
        if (repository.existsByUsername(request.user())) { // username existe?
            throw new DataIntegrityViolationException("Username \""+request.user()+"\" ya existe");
        }
        User user = User.builder()
                .username(request.user())
                .mail(request.mail())
                .password(passwordEncoder.encode(request.password()))
                .build();
        final User savedUser = repository.save(user);
        final String jwtToken = jwtUseCase.generateToken(savedUser);
        final String refreshToken = jwtUseCase.generateRefreshToken(user); // @TODO ??????????????
        
        Instant expirationInstant = Instant.now().plusMillis(expiration);
        savedUser.setToken(jwtToken);
        savedUser.setExpiresIn(expirationInstant);
        return savedUser;
    }


    @Override
    public User authenticate(final UserLoginDTO request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.user(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }
        // Buscar el usuario autenticado en la base de datos
        return repository.findByUsername(request.user())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no existente"));
    }


@Override
public User updateCred(@NotNull final String authentication, UpdateCredRequest request) {
    // Obtener el usuario autenticado
    if (authentication == null || !authentication.startsWith("Bearer ")) {
        throw new IllegalArgumentException("Header de autenticacion invalido");
    }
    final String authToken = authentication.substring(7);
    System.out.println("USERNAME: ");
    final String currentUsername = jwtUseCase.extractUsername(authToken);
    if (currentUsername == null) {
        throw new IllegalArgumentException("Token invalido");
    }
    User user = this.repository.findByUsername(currentUsername).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    // email ya en uso?
    if (repository.existsByUsername(request.newUsername()) && !currentUsername.equals(request.newUsername())) {
        throw new IllegalArgumentException("Username ya en uso");
    }
    // actualizar las credenciales
    user.setUsername(request.newUsername());
    user.setPassword(passwordEncoder.encode(request.newPassword()));

    // guardar el usuario actualizado
    return repository.save(user);
}


    /*
     * private void saveUserToken(UserEntity userEntity, String jwtToken) {
     * final Token token = Token.builder()
     * .user(userEntity)
     * .token(jwtToken)
     * .tokenType(Token.TokenType.BEARER)
     * .isExpired(false)
     * .isRevoked(false)
     * .build();
     * tokenRepository.save(token);
     * }
     * 
     * private void revokeAllUserTokens(final UserEntity userEntity) {
     * final List<Token> validUserTokens =
     * tokenRepository.findAllValidTokenByUser(userEntity.getId());
     * if (!validUserTokens.isEmpty()) {
     * validUserTokens.forEach(token -> {
     * token.setIsExpired(true);
     * token.setIsRevoked(true);
     * });
     * tokenRepository.saveAll(validUserTokens);
     * }
     * }
     */

    @Override
    public User refreshToken(@NotNull final String authentication) {
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }

        // Extraer el refreshToken del header
        final String refreshToken = authentication.substring(7);

        // Obtener el username del refreshToken
        final String userEmail = jwtUseCase.extractUsername(refreshToken);
        if (userEmail == null) {
            throw new IllegalArgumentException("Invalid refresh token: username extraction failed");
        }

        // Buscar el usuario con ese email
        final User user = this.repository.findByMail(userEmail).orElseThrow(() ->
                new IllegalArgumentException("User not found with email: " + userEmail)
        );

        // Verificar si el token es válido
        final boolean isTokenValid = jwtUseCase.isTokenValid(refreshToken);
        if (!isTokenValid) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        // Generar un nuevo token
        return user;
    }

    
    @Override
    public TokenData generateTokens(User user) {
        final String accessToken = jwtUseCase.generateToken(user);
        final String refreshToken = jwtUseCase.generateRefreshToken(user);

        Instant expirationInstant = Instant.now().plusMillis(expiration);

        return new TokenData(accessToken, expirationInstant);
    }
}