package org.example.backend.databaseapi.jpa.liga;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.exception.MetodoNoPermitido;
import org.example.backend.databaseapi.application.port.out.liga.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.partida.Estado;
import org.example.backend.databaseapi.jpa.bot.BotJpaAdapter;
import org.example.backend.databaseapi.jpa.bot.BotJpaEntity;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaAdapter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.example.backend.databaseapi.application.exception.ValidationException;
import org.springframework.web.server.MethodNotAllowedException;

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

        if(ligaJpaRepository.existsByNombre(liga.getNombre())) {
            return Optional.empty();
        }

        LigaJpaEntity ligaJpa = LigaJpaEntity.builder()
                .nombre(liga.getNombre())
                .usuario(
                        usuarioJpaAdapter.getUser(liga.getUsuario().value())
                                .orElseThrow()
                )
                .estado(Estado.PENDANT)
                .rondas(liga.getRondas())
                .matchTime(liga.getMatchTime())
                .botsLiga(liga.getBotsLiga()
                        .stream()
                        .map(id->botJpaAdapter.getJpaBot(id.value()).orElseThrow())
                        .toList()
                )
                .urlImagen(liga.getUrlImagen())
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
    public Liga deleteLiga(Integer ligaId,Integer userId) {
        LigaJpaEntity liga=ligaJpaRepository.findById(ligaId)
                .orElseThrow();
        if(!Objects.equals(liga.getUsuario().getUserId(), userId)){
            throw new MetodoNoPermitido("No eres el dueño de la liga");
        }
        ligaJpaRepository.deleteById(ligaId);
        return ligaJpaMapper.toDomain(liga);
    }

    @Override
    @Transactional
    public Liga updateLiga(Liga liga,Integer id) {
        Integer currentOwner=ligaJpaRepository.findById(id)
                .orElseThrow()
                .getUsuario()
                .getUserId();
        if(!currentOwner.equals(liga.getUsuario().value())){
            throw new MetodoNoPermitido("No puedes actualizar una liga de otro usuarip");
        }
        LigaJpaEntity ligaJpa = LigaJpaEntity.builder()
                .ligaId(id)
                .nombre(liga.getNombre())
                .usuario(
                        usuarioJpaAdapter.getUser(liga.getUsuario().value())
                                .orElseThrow()
                )
                .estado(Estado.PENDANT)
                .rondas(liga.getRondas())
                .matchTime(liga.getMatchTime())
                .botsLiga(liga.getBotsLiga()
                        .stream()
                        .map(botId -> botJpaAdapter.getJpaBot(botId.value())
                                .orElseThrow())
                        .toList()
                )
                .urlImagen(liga.getUrlImagen())
                .build();
        return ligaJpaMapper.toDomain(ligaJpaRepository.save(ligaJpa));
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
