package com.debateia.application.service;

import com.debateia.adapter.in.rest.UserDTOLogin;
import com.debateia.adapter.in.rest.UserDTORegister;
import com.debateia.adapter.in.rest.UpdateCredRequest;
import com.debateia.adapter.out.persistence.UserResponseDTO;

import jakarta.validation.constraints.NotNull;

public interface AuthUseCase {
    public UserResponseDTO register(final UserDTORegister request);

    public UserResponseDTO authenticate(final UserDTOLogin request);

    public UserResponseDTO updateCred(@NotNull final String authentication, final UpdateCredRequest request);

    public UserResponseDTO refreshToken(@NotNull final String authentication);
}
