package com.debateia.JWT_viejo;

import com.debateia.dto_viejo.AuthRequest;
import com.debateia.dto_viejo.RegisterRequest;
import com.debateia.dto_viejo.TokenResponse;
import com.debateia.dto_viejo.UpdateCredRequest;

import jakarta.validation.constraints.NotNull;

public interface AuthUseCase {
    public TokenResponse register(final RegisterRequest request);
    public TokenResponse authenticate(final AuthRequest request);
    public TokenResponse updateCred(@NotNull final String authentication,final UpdateCredRequest request);
    public TokenResponse refreshToken(@NotNull final String authentication);
}
