package org.example.backend.databaseapi.jpa.liga;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.port.out.liga.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.jpa.bot.BotJpaAdapter;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaAdapter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import org.example.backend.databaseapi.application.exception.ValidationException;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class LigaJpaAdapter implements CreateLigaPort, FindAllLigasPort, FindLigaPort, FindLigaUsuarioPort, DeleteLigaPort, UpdateLigaPort, AddBotLigaPort {

    private final LigaJpaMapper ligaJpaMapper;
    private final LigaJpaRepository ligaJpaRepository;
    @Lazy
    private final UsuarioJpaAdapter usuarioJpaAdapter;
    @Lazy
    private final BotJpaAdapter botJpaAdapter;

    @Override
    @Transactional
    public Optional<Liga> createLiga(Liga liga) {
        if (liga.getNombre() == null || liga.getNombre().isEmpty() ||
                liga.getUsuario() == null ||
                liga.getBotsLiga() == null || liga.getBotsLiga().isEmpty()) {

            throw new ValidationException("Validation failed: " +
                    (liga.getNombre() == null || liga.getNombre().isEmpty() ? "Name is required. " : "") +
                    (liga.getUsuario() == null ? "User is required. " : "") +
                    (liga.getBotsLiga() == null || liga.getBotsLiga().isEmpty() ? "Bots are required. " : "")
            );
        }

        if(ligaJpaRepository.existsByNombre(liga.getNombre())) {
            return Optional.empty();
        }

        int numBots = liga.getBotsLiga().size();
        int maxRondas = (numBots * (numBots - 1)) / 2;

        if (liga.getRondas() > maxRondas) {
            throw new ValidationException("Número de partidas excede el máximo para " + numBots + " bots: " + maxRondas);
        } else if (liga.getRondas() <= 0) {
            liga.setRondas(maxRondas);
        }

        LigaJpaEntity ligaJpa = LigaJpaEntity.builder()
                .nombre(liga.getNombre())
                .usuario(
                        usuarioJpaAdapter.getUser(liga.getUsuario().value())
                                .orElseThrow()
                )
                .rondas(liga.getRondas())
                .botsLiga(
                    liga.getBotsLiga()
                            .stream()
                            .map(
                                botId -> botJpaAdapter.getJpaBot(botId.value())
                                        .orElseThrow()
                            )
                            .toList()
                )
                .build();
        return Optional.of(ligaJpaMapper.toDomain(ligaJpaRepository.save(ligaJpa)));
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

    @Override
    @Transactional
    public Liga deleteLiga(Integer ligaId) {
        LigaJpaEntity liga=ligaJpaRepository.findById(ligaId)
                .orElseThrow();
        ligaJpaRepository.deleteById(ligaId);
        return ligaJpaMapper.toDomain(liga);
    }

    @Override
    @Transactional
    public Liga updateLiga(Liga liga,Integer id) {
        LigaJpaEntity entity=LigaJpaEntity.builder()
                .ligaId(id)
                .botsLiga(
                        liga.getBotsLiga()
                                .stream()
                                .map(
                                        botId -> botJpaAdapter.getJpaBot(botId.value())
                                                .orElseThrow()
                                )
                                .toList()

                )
                .nombre(liga.getNombre())
                .usuario(usuarioJpaAdapter.getUser(liga.getUsuario().value())
                        .orElseThrow()
                )
                .build();
        return ligaJpaMapper.toDomain(ligaJpaRepository.save(entity));
    }

    @Override
    @Transactional
    public void addBotLiga(Integer idBot, Integer idLiga) {
        LigaJpaEntity entity= ligaJpaRepository.findById(idLiga)
                .orElseThrow();
        entity.getBotsLiga().add(botJpaAdapter.getJpaBot(idBot)
                .orElseThrow()
        );
        ligaJpaRepository.save(entity);
    }
}
