package org.example.backend.databaseapi.application.dto.usuario;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UserDTOLogin {
    private String email;
    private String password;

}
