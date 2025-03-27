package org.example.backend.databaseapi.domain.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bot {

    private final BotId idBot;
    private UsuarioId usuario;
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
