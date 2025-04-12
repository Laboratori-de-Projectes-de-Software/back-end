package com.debateia.application.service;

import com.debateia.adapter.out.persistence.AuthMapper;
import com.debateia.adapter.out.persistence.entities.UserEntity;
import com.debateia.application.jwt.JwtService;
import com.debateia.adapter.in.web.dto.request.UpdateCredRequest;
import com.debateia.adapter.in.web.dto.request.UserDTOLogin;
import com.debateia.adapter.in.web.dto.request.UserDTORegister;
import com.debateia.adapter.out.persistence.UserResponseDTO;
import com.debateia.application.ports.out.persistence.UserRepository;
import com.debateia.domain.User;
import com.debateia.application.ports.out.persistence.TokenRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
public ResponseEntity<?> register(final UserDTORegister request) {
    if (repository.existsByMail(request.mail())) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Email already exists.");
    }

    // Verifica si el nombre de usuario ya está registrado
    if (repository.existsByUsername(request.user())) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Username already exists.");
    }

    User user = User.builder()
            .username(request.user())
            .mail(request.mail())
            .password(passwordEncoder.encode(request.password()))
            .build();
    final UserEntity userEntity = authMapper.usuarioToEntity(user);

    try {
        final UserEntity savedUserEntity = repository.save(userEntity);
        user = authMapper.entityToUsuario(savedUserEntity);

        final String jwtToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);

        // Obtener la fecha actual (Instant)
        Instant now = Instant.now();
        Instant expirationInstant = now.plusMillis(expiration);
        LocalDate expirationDate = expirationInstant.atZone(ZoneId.systemDefault()).toLocalDate();

        // Aquí se crea el DTO de respuesta y se pasa como cuerpo
        UserResponseDTO response = new UserResponseDTO(jwtToken, expirationDate, user.getUsername());
        return ResponseEntity.ok(response); // Devolver la respuesta correcta con la información del usuario
    } catch (Exception e) {
        // Loggea el error para obtener detalles
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Registration failed due to an internal error: " + e.getMessage());
    }
}


    @Override
    public ResponseEntity<?> authenticate(final UserDTOLogin request) {
        
        try {
            // Intentar autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.user(),
                            request.password()));
                            
            User user = User.builder().username(request.user())
                    .password(passwordEncoder.encode(request.password())).build();
            UserEntity userEntity = authMapper.usuarioToEntity(user);
            // Buscar al usuario en la base de datos
            userEntity = repository.findByUsername(userEntity.getUsername())
                    .orElseThrow(() -> null);
            user = authMapper.entityToUsuario(userEntity);
            
            // Generar tokens
            final String accessToken = jwtService.generateToken(user);
            final String refreshToken = jwtService.generateRefreshToken(user);

            // Revocar tokens anteriores y guardar el nuevo
            // revokeAllUserTokens(user);
            // saveUserToken(user, accessToken);

            // Devolver la respuesta con los tokens
            // Obtener la fecha actual (Instant)
        Instant now = Instant.now();

        // Sumar los milisegundos de expiración al tiempo actual
        Instant expirationInstant = now.plusMillis(expiration);

        // Convertir el Instant a LocalDate
        LocalDate expirationDate = expirationInstant.atZone(ZoneId.systemDefault()).toLocalDate();
        return ResponseEntity.ok(new UserResponseDTO(accessToken, expirationDate, user.getUsername()));

        }  catch (BadCredentialsException e) {
        // Si las credenciales son incorrectas, devolver 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)  // 401 Unauthorized
                .body("Credenciales incorrectas");  // Respuesta vacía
    } catch (UsernameNotFoundException e) {
        // Si el usuario no existe, devolver 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND)  // 404 Not Found
                .body("Usuario no existente");  // Usuario no encontrado
    } catch (Exception e) {
        // Para otros errores generales
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)  // 501 Not Implemented
                .body("Error intenro");  // Respuesta vacía
    }
    }

    @Override
    public UserResponseDTO updateCred(@NotNull final String authentication, UpdateCredRequest request) {
        // Obtener el usuario autenticado
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String authToken = authentication.substring(7);
        System.out.println("USERNAME: ");
        final String currentUsername = jwtService.extractUsername(authToken);
        if (currentUsername == null) {
            return null;
        }
        /*
         * User user = User.builder().email(request.newEmail())
         * .password(passwordEncoder.encode(request.newPassword())).build();
         * System.out.println("HOALAAALALAMALA: " + user.getEmail());
         * UserEntity userEntity = authMapper.usuarioToEntity(user);
         * System.out.println("USER ENTITY: " + userEntity.getEmail());
         */
        
        UserEntity userEntity = this.repository.findByUsername(currentUsername).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(authentication);

        if (!isTokenValid) {
            return null;
        }

        // Verificar si el nuevo email ya está en uso
        if (repository.existsByUsername(request.newUsername()) && !currentUsername.equals(request.newUsername())) {
            return null;
        }

        // Actualizar las credenciales
        userEntity.setUsername(request.newUsername());
        userEntity.setPassword(passwordEncoder.encode(request.newPassword())); // Asegúrate de codificar la nueva
                                                                               // contraseña
        repository.save(userEntity);
        User user = authMapper.entityToUsuario(userEntity);
        // Generar tokens
        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        // Obtener la fecha actual (Instant)
        Instant now = Instant.now();

        // Sumar los milisegundos de expiración al tiempo actual
        Instant expirationInstant = now.plusMillis(expiration);

        // Convertir el Instant a LocalDate
        LocalDate expirationDate = expirationInstant.atZone(ZoneId.systemDefault()).toLocalDate();
        return new UserResponseDTO(accessToken, expirationDate, user.getUsername());
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

        final UserEntity userEntity = this.repository.findByMail(userEmail).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(authentication);
        if (!isTokenValid) {
            return null;
        }
        User user = authMapper.entityToUsuario(userEntity);
        final String accessToken = jwtService.generateRefreshToken(user);
        // revokeAllUserTokens(user);
        // saveUserToken(user, accessToken);
        // Obtener la fecha actual (Instant)
        Instant now = Instant.now();

        // Sumar los milisegundos de expiración al tiempo actual
        Instant expirationInstant = now.plusMillis(expiration);

        // Convertir el Instant a LocalDate
        LocalDate expirationDate = expirationInstant.atZone(ZoneId.systemDefault()).toLocalDate();
        return new UserResponseDTO(accessToken, expirationDate, user.getUsername());
    }

}
