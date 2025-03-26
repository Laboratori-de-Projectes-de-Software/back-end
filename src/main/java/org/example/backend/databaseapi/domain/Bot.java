package org.example.backend.databaseapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bot {

    private int idBot;
    private Usuario usuario;
    private List<Liga> ligasBot;
    private String cualidad;
    private String url;
    private String imagen;
    private String nombre;

    @Override
    public String toString() {
        return "Bot{" +
                "id_bot=" + idBot +
                ", usuario=" + usuario +
                ", cualidad='" + cualidad + '\'' +
                ", url='" + url + '\'' +
                ", imagen='" + imagen + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
