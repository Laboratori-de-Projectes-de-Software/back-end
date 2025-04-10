package org.example.backend.databaseapi.application.dto.usuario;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UserDTORegister {

    private String user;
    private String password; // TODO: debería hacerse un check para no permitir contraseñas inseguras
    private String email;
}
