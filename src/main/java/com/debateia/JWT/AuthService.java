package com.debateia.JWT;


import com.debateia.domain.AuthMapper;
import com.debateia.dto.AuthRequest;
import com.debateia.dto.RegisterRequest;
import com.debateia.dto.TokenResponse;
import com.debateia.dto.UpdateCredRequest;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public TokenResponse register(final RegisterRequest request) {
        if(!request.password().equals(request.confirmPassword())){
            return null;
        }
        UserDto userDto=UserDto.builder().name(request.name()).email(request.email()).password(passwordEncoder.encode(request.password())).build();
        final User user= AuthMapper.INSTANCE.dtoToUsuario(userDto);
        /* final User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build(); */

        final User savedUser = repository.save(user);
        userDto=AuthMapper.INSTANCE.usuarioToDto(savedUser);
        final String jwtToken = jwtService.generateToken(userDto);
        final String refreshToken = jwtService.generateRefreshToken(userDto);

        //saveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }
    @Override
    public TokenResponse authenticate(final AuthRequest request) {
    try {
        // Intentar autenticar al usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        UserDto userDto=UserDto.builder().email(request.email()).password(passwordEncoder.encode(request.password())).build();
        User user= AuthMapper.INSTANCE.dtoToUsuario(userDto);
        // Buscar al usuario en la base de datos
        user = repository.findByEmail(user.getEmail())
                .orElseThrow(() -> null);
        userDto = AuthMapper.INSTANCE.usuarioToDto(user);
        // Generar tokens
        final String accessToken = jwtService.generateToken(userDto);
        final String refreshToken = jwtService.generateRefreshToken(userDto);

        // Revocar tokens anteriores y guardar el nuevo
        //revokeAllUserTokens(user);
        //saveUserToken(user, accessToken);

        // Devolver la respuesta con los tokens
        return new TokenResponse(accessToken, refreshToken);

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
public TokenResponse updateCred(@NotNull final String authentication,UpdateCredRequest request) {
    // Obtener el usuario autenticado
    if (authentication == null || !authentication.startsWith("Bearer ")) {
        throw new IllegalArgumentException("Invalid auth header");
    }
    final String authToken = authentication.substring(7);
    final String currentEmail = jwtService.extractUsername(authToken);
    if (currentEmail == null) {
        return null;
    }
    UserDto userDto=UserDto.builder().email(request.newEmail()).password(passwordEncoder.encode(request.newPassword())).build();
    User user= AuthMapper.INSTANCE.dtoToUsuario(userDto);
    user = this.repository.findByEmail(user.getEmail()).orElseThrow();
    final boolean isTokenValid = jwtService.isTokenValid(authToken, user);
    if (!isTokenValid) {
        return null;
    }
    
    // Verificar si el nuevo email ya está en uso
    if (repository.existsByEmail(request.newEmail()) && !currentEmail.equals(request.newEmail())) {
        return null;
    }

    // Actualizar las credenciales
    user.setEmail(request.newEmail());
    user.setPassword(passwordEncoder.encode(request.newPassword())); // Asegúrate de codificar la nueva contraseña
    repository.save(user);
    userDto=AuthMapper.INSTANCE.usuarioToDto(user);
     // Generar tokens
     final String accessToken = jwtService.generateToken(userDto);
     final String refreshToken = jwtService.generateRefreshToken(userDto);
        return new TokenResponse(accessToken,refreshToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        final Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(final User user) {
        final List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setIsExpired(true);
                token.setIsRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }
    @Override
    public TokenResponse refreshToken(@NotNull final String authentication) {

        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String refreshToken = authentication.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return null;
        }

        final User user = this.repository.findByEmail(userEmail).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(refreshToken, user);
        if (!isTokenValid) {
            return null;
        }
        UserDto userDto=AuthMapper.INSTANCE.usuarioToDto(user);
        final String accessToken = jwtService.generateRefreshToken(userDto);
        //revokeAllUserTokens(user);
        //saveUserToken(user, accessToken);

        return new TokenResponse(accessToken, refreshToken);
    }


}
