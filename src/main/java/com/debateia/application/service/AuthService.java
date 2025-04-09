package com.debateia.application.service;

import com.debateia.adapter.out.persistence.AuthMapper;
import com.debateia.adapter.out.persistence.Token;
import com.debateia.adapter.out.persistence.UserEntity;
import com.debateia.application.jwt.JwtService;
import com.debateia.adapter.in.web.UserDTOLogin;
import com.debateia.adapter.in.web.UserDTORegister;
import com.debateia.adapter.out.persistence.UserResponseDTO;
import com.debateia.application.ports.out.persistence.UserRepository;
import com.debateia.domain.User;
import com.debateia.adapter.in.web.UpdateCredRequest;
import com.debateia.application.ports.out.persistence.TokenRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private AuthMapper authMapper;
    @Value("${spring.application.security.jwt.expiration}")
    private int expiration; // Elimina "final"

    @Override
    public UserResponseDTO register(final UserDTORegister request) {
        if (!request.password().equals(request.confirmPassword())) {
            return null;
        }
        User user = User.builder().username(request.username()).email(request.email())
                .password(passwordEncoder.encode(request.password())).build();
        final UserEntity userEntity = authMapper.usuarioToEntity(user);
        /*
         * final User user = User.builder()
         * .name(userDto.getName())
         * .email(userDto.getEmail())
         * .password(passwordEncoder.encode(userDto.getPassword()))
         * .build();
         */

        final UserEntity savedUserEntity = repository.save(userEntity);
        user = authMapper.entityToUsuario(savedUserEntity);
        final String jwtToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        // saveUserToken(savedUser, jwtToken);
        return new UserResponseDTO(jwtToken, expiration, user.getUsername(), userEntity.getId());
    }

    @Override
    public UserResponseDTO authenticate(final UserDTOLogin request) {
        try {
            // Intentar autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()));
            User user = User.builder().email(request.email())
                    .password(passwordEncoder.encode(request.password())).build();
            UserEntity userEntity = authMapper.usuarioToEntity(user);
            // Buscar al usuario en la base de datos
            userEntity = repository.findByEmail(userEntity.getEmail())
                    .orElseThrow(() -> null);
            user = authMapper.entityToUsuario(userEntity);
            // Generar tokens
            final String accessToken = jwtService.generateToken(user);
            final String refreshToken = jwtService.generateRefreshToken(user);

            // Revocar tokens anteriores y guardar el nuevo
            // revokeAllUserTokens(user);
            // saveUserToken(user, accessToken);

            // Devolver la respuesta con los tokens
            return new UserResponseDTO(accessToken, expiration, user.getUsername(), userEntity.getId());

        } catch (BadCredentialsException e) {
            // Manejar credenciales incorrectas
            return null;
        } catch (IllegalArgumentException e) {
            // Manejar usuario no encontrado
            return null;
        } catch (Exception e) {
            // Manejar otros errores
            return null;
        }
    }

    @Override
    public UserResponseDTO updateCred(@NotNull final String authentication, UpdateCredRequest request) {
        // Obtener el usuario autenticado
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        final String currentEmail = jwtService.extractUsername(authToken);
        if (currentEmail == null) {
            return null;
        }
        /*
         * User user = User.builder().email(request.newEmail())
         * .password(passwordEncoder.encode(request.newPassword())).build();
         * System.out.println("HOALAAALALAMALA: " + user.getEmail());
         * UserEntity userEntity = authMapper.usuarioToEntity(user);
         * System.out.println("USER ENTITY: " + userEntity.getEmail());
         */
        UserEntity userEntity = this.repository.findByEmail(currentEmail).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(authentication);

        if (!isTokenValid) {
            return null;
        }

        // Verificar si el nuevo email ya está en uso
        if (repository.existsByEmail(request.newEmail()) && !currentEmail.equals(request.newEmail())) {
            return null;
        }

        // Actualizar las credenciales
        userEntity.setEmail(request.newEmail());
        userEntity.setPassword(passwordEncoder.encode(request.newPassword())); // Asegúrate de codificar la nueva
                                                                               // contraseña
        repository.save(userEntity);
        User user = authMapper.entityToUsuario(userEntity);
        // Generar tokens
        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        return new UserResponseDTO(accessToken, expiration, user.getUsername(), userEntity.getId());
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
    public UserResponseDTO refreshToken(@NotNull final String authentication) {

        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String refreshToken = authentication.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return null;
        }

        final UserEntity userEntity = this.repository.findByEmail(userEmail).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(authentication);
        if (!isTokenValid) {
            return null;
        }
        User user = authMapper.entityToUsuario(userEntity);
        final String accessToken = jwtService.generateRefreshToken(user);
        // revokeAllUserTokens(user);
        // saveUserToken(user, accessToken);

        return new UserResponseDTO(accessToken, expiration, user.getUsername(), userEntity.getId());
    }

}
