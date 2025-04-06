package org.example.backend.databaseapi.domain.liga;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Liga {

    private final LigaId ligaId;
    private List<BotId> botsLiga;
    private UsuarioId usuario;
    private String nombre;
    private int rondas;

    @Override
    public String toString() {
        return "Liga{" +
                "ligaId=" + ligaId +
                ", usuario=" + usuario +
                ", nombre='" + nombre +
                ", rondas='" + rondas + '\'' +
                '}';
    }
}
