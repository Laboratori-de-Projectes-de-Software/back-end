package org.example.backend.databaseapi.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenAndPassword {
    private String token;
    private String nuevaPassword; // TODO: debería hacerse un check para no permitir contraseñas inseguras
}

