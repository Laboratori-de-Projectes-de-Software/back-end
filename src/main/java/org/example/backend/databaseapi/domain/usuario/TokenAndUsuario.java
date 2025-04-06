package org.example.backend.databaseapi.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenAndUsuario {
    private String token;
    private Usuario usuario;
}