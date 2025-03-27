package org.example.backend.databaseapi.domain.liga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.usuario.Usuario;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Liga {

    private LigaId ligaId;
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
