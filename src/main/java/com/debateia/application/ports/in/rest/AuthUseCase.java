package com.debateia.application.ports.in.rest;

import com.debateia.adapter.in.rest.auth.UpdateCredRequest;
import com.debateia.adapter.in.rest.auth.UserLoginDTO;
import com.debateia.adapter.in.rest.auth.UserDTORegister;

import com.debateia.domain.User;

import com.debateia.adapter.in.rest.auth.TokenData;

import jakarta.validation.constraints.NotNull;

public interface AuthUseCase {
    User register(final UserDTORegister request);

    User authenticate(final UserLoginDTO request);

    User updateCred(@NotNull final String authentication, final UpdateCredRequest request);

    User refreshToken(@NotNull final String authentication);
    
    TokenData generateTokens(User user);
}
