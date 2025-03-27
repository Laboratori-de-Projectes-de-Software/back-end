package org.example.backend.databaseapi.jpa.bot;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.databaseapi.application.port.out.bot.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.jpa.liga.LigaJpaAdapter;
import org.example.backend.databaseapi.jpa.liga.LigaJpaMapper;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class BotJpaAdapter implements CreateBotPort, FindBotPort, UpdateBotPort, DeleteBotPort, FindAllUserBots, FindAllBotsPort {

    private final BotJpaRepository botJpaRepository;
    private final UsuarioJpaAdapter usuarioJpaAdapter;
    private final BotJpaMapper botJpaMapper;
    private final LigaJpaAdapter ligaJpaAdapter;

    @Override
    @Transactional
    public Optional<Bot> createBot(Bot bot) {
        if(!botJpaRepository.existsByNombreOrUrl(bot.getNombre(),bot.getUrl())){
            BotJpaEntity newbot=BotJpaEntity.builder()
                    .url(bot.getUrl())
                    .cualidad(bot.getCualidad())
                    .ligasBot(bot.getLigasBot()
                            .stream()
                            .map(
                                    ligaId -> ligaJpaAdapter.getLiga(ligaId.value())
                            )
                            .toList()
                    )
                    .imagen(bot.getImagen())
                    .usuario(
                            usuarioJpaAdapter.getUser(bot.getUsuario().value())
                                    .orElseThrow()
                    )
                    .nombre(bot.getNombre())
                    .build();
            return Optional.of(botJpaMapper.toDomain(botJpaRepository.save(newbot)));
        }
        return Optional.empty();
    }

    @Override
    public void deleteBot(int id_bot) {
        botJpaRepository.deleteById(id_bot);
    }

    @Override
    public Optional<Bot> findBot(int id_bot) {
        return botJpaRepository.findById(id_bot).map(botJpaMapper::toDomain);
    }

    @Override
    @Transactional
    public Bot updateBot(Bot changedBot, Integer id) {
        return botJpaRepository.findById(id)
                .map(bot->{
                        bot.setCualidad(changedBot.getCualidad());
                        bot.setImagen(changedBot.getImagen());
                        bot.setNombre(changedBot.getNombre());
                        bot.setLigasBot(changedBot.getLigasBot()
                                .stream()
                                .map(
                                        ligaId -> ligaJpaAdapter.getLiga(ligaId.value())
                                )
                                .toList()
                        );
                        bot.setUrl(changedBot.getUrl());
                        return botJpaMapper.toDomain(botJpaRepository.save(bot));
                })
                .orElseGet(
                        ()->{
                        BotJpaEntity newbot=BotJpaEntity.builder()
                                .url(changedBot.getUrl())
                                .cualidad(changedBot.getCualidad())
                                .ligasBot(changedBot.getLigasBot()
                                        .stream()
                                        .map(
                                                ligaId -> ligaJpaAdapter.getLiga(ligaId.value())
                                        )
                                        .toList()
                                )
                                .imagen(changedBot.getImagen())
                                .usuario(
                                        usuarioJpaAdapter.getUser(changedBot.getUsuario().value())
                                                .orElseThrow()
                                )
                                .nombre(changedBot.getNombre())
                                .build();
                            return botJpaMapper.toDomain(botJpaRepository.save(newbot));
                        }
                );
    }

    @Override
    @Transactional
    public List<Bot> findAllBots(int id_user) {
        return botJpaRepository.findByUsuario_UserId(id_user)
                .stream()
                .map(botJpaMapper::toDomain)
                .toList();

    }

    @Override
    public List<Bot> findAllBots() {

        return botJpaRepository.findAll()
                .stream()
                .map(botJpaMapper::toDomain)
                .toList();

    }

    public Optional<BotJpaEntity> getJpaBot(int id_bot){
        return botJpaRepository.findById(id_bot);
    }
}
