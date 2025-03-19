package com.debateia.domain_viejo;

public interface UpdateUserCredentialsUseCase {
    UsuarioDto updateCredentials(Long userId, UpdateCredentialsDto updateCredentialsDto);
}
