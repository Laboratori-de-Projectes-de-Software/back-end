package org.example.backend.databaseapi.domain.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {

    private final UsuarioId userId;
    private String nombre;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Email email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String imagen;

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + userId +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
