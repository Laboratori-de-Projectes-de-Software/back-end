package com.debateia.application.ports.in.rest;

import com.debateia.adapter.in.web.dto.request.UpdateCredRequest;
import com.debateia.adapter.in.web.dto.request.UserDTOLogin;
import com.debateia.adapter.in.web.dto.request.UserDTORegister;

import com.debateia.domain.User;
import org.springframework.http.ResponseEntity;

import com.debateia.adapter.in.web.dto.response.UserResponseDTO;
import com.debateia.adapter.out.persistence.entities.UserEntity;

import jakarta.validation.constraints.NotNull;

public interface AuthUseCase {
    User register(final UserDTORegister request);

    User authenticate(final UserDTOLogin request);

    User updateCred(@NotNull final String authentication, final UpdateCredRequest request);

    User refreshToken(@NotNull final String authentication);
}
