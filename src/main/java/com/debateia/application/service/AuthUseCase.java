package com.debateia.application.service;

import com.debateia.adapter.in.web.dto.request.UpdateCredRequest;
import com.debateia.adapter.in.web.dto.request.UserDTOLogin;
import com.debateia.adapter.in.web.dto.request.UserDTORegister;

import org.springframework.http.ResponseEntity;

import com.debateia.adapter.out.persistence.UserResponseDTO;
import com.debateia.adapter.out.persistence.entities.UserEntity;

import jakarta.validation.constraints.NotNull;

public interface AuthUseCase {
    public ResponseEntity<?> register(final UserDTORegister request);

    public ResponseEntity<?> authenticate(final UserDTOLogin request);

    public UserResponseDTO updateCred(@NotNull final String authentication, final UpdateCredRequest request);

    public UserResponseDTO refreshToken(@NotNull final String authentication);
}
