package org.example.backend.databaseapi.jpa.liga;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.port.out.liga.CreateLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindAllLigasPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaUsuarioPort;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.jpa.bot.BotJpaAdapter;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaAdapter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class LigaJpaAdapter implements CreateLigaPort, FindAllLigasPort, FindLigaPort, FindLigaUsuarioPort {

    private final LigaJpaMapper ligaJpaMapper;
    private final LigaJpaRepository ligaJpaRepository;
    @Lazy
    private final UsuarioJpaAdapter usuarioJpaAdapter;
    @Lazy
    private final BotJpaAdapter botJpaAdapter;

    @Override
    @Transactional
    public Liga createLiga(Liga liga) {
        LigaJpaEntity ligaJpa=LigaJpaEntity.builder()
                .nombre(liga.getNombre())
                .usuario(usuarioJpaAdapter.getUser(liga.getUsuario().value())
                        .orElseThrow())
                /*
                TODO:ES POSIBLE QUE SE TENGA QUE CREAR UNA FUNCION PARA ESTO AL SER UN ENDPOINT DIFERENTE
                .botsLiga(
                        liga.getBotsLiga()
                                .stream()
                                .map(
                                    botId -> botJpaAdapter.getJpaBot(botId.value())
                                            .orElseThrow()
                                )
                                .toList()

                )
                 */
                .build();
        return ligaJpaMapper.toDomain(
                ligaJpaRepository.save(
                        ligaJpa
                )
        );
    }

    @Override
    public List<Liga> findAllLigas() {
        return ligaJpaRepository.findAll()
                .stream()
                .map(ligaJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Liga> findLiga(int id_liga) {
        return ligaJpaRepository.findById(id_liga)
                .map(ligaJpaMapper::toDomain);

    }

    @Override
    public List<Liga> findLigasUsuario(int id_usuario) {
        return ligaJpaRepository.findByUsuario_UserId(id_usuario)
                .stream()
                .map(ligaJpaMapper::toDomain)
                .toList();
    }

    public LigaJpaEntity getLiga(int ligaId){
        return ligaJpaRepository.findById(ligaId)
                .orElseThrow();
    }
}
