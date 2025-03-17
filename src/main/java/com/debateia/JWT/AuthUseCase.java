package com.debateia.JWT;

import com.debateia.dto.AuthRequest;
import com.debateia.dto.RegisterRequest;
import com.debateia.dto.TokenResponse;
import com.debateia.dto.UpdateCredRequest;

import jakarta.validation.constraints.NotNull;

public interface AuthUseCase {
    public TokenResponse register(final RegisterRequest request);
    public TokenResponse authenticate(final AuthRequest request);
    public TokenResponse updateCred(@NotNull final String authentication,final UpdateCredRequest request);
    public TokenResponse refreshToken(@NotNull final String authentication);
}
