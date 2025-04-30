<<<<<<<< HEAD:src/main/java/org/example/backend/databaseapi/domain/usuario/TokenAndUsuario.java
package org.example.backend.databaseapi.domain.usuario;
========
package org.example.backend.databaseapi.application.dto.usuario;
>>>>>>>> 121ac9a (DTO Objects + Yaml):src/main/java/org/example/backend/databaseapi/application/dto/usuario/AuthResponse.java

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