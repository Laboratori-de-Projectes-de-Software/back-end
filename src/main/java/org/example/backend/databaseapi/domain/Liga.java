package org.example.backend.databaseapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Liga {

    private Integer ligaId;
    private List<Bot> botsLiga;
    private Usuario usuario;
    private String nombre;

    @Override
    public String toString() {
        return "Liga{" +
                "ligaId=" + ligaId +
                ", usuario=" + usuario +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
