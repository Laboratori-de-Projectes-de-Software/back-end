package com.debateia.application.service;



import com.debateia.adapter.in.web.AuthRequest;
import com.debateia.adapter.in.web.RegisterRequest;
import com.debateia.adapter.in.web.UpdateCredRequest;
import com.debateia.adapter.out.persistence.TokenResponse;

import jakarta.validation.constraints.NotNull;

public interface AuthUseCase {
    public TokenResponse register(final RegisterRequest request);
    public TokenResponse authenticate(final AuthRequest request);
    public TokenResponse updateCred(@NotNull final String authentication,final UpdateCredRequest request);
    public TokenResponse refreshToken(@NotNull final String authentication);
}
