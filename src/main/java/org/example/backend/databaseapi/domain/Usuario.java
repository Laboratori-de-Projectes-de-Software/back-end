package org.example.backend.databaseapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {

    private Integer userId;
    private String nombre;
    private String email;
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
