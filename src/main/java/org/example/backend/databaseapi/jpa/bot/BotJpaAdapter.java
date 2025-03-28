package org.example.backend.databaseapi.jpa.bot;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.backend.databaseapi.application.port.out.bot.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.jpa.liga.LigaJpaAdapter;
import org.example.backend.databaseapi.jpa.liga.LigaJpaEntity;
import org.example.backend.databaseapi.jpa.liga.LigaJpaMapper;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaAdapter;
import org.example.backend.databaseapi.jpa.usuario.UsuarioJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class BotJpaAdapter implements CreateBotPort, FindBotPort, UpdateBotPort, DeleteBotPort, FindAllUserBots, FindAllBotsPort {

    private final BotJpaRepository botJpaRepository;
    @Lazy
    private final UsuarioJpaAdapter usuarioJpaAdapter;
    @Lazy
    private final LigaJpaAdapter ligaJpaAdapter;
    private final BotJpaMapper botJpaMapper;
    private final UsuarioJpaMapper usuarioJpaMapper;



    @Override
    @Transactional
    public Optional<Bot> createBot(Bot bot) {
        if(!botJpaRepository.existsByNombreOrUrl(bot.getNombre(),bot.getUrl())){
            BotJpaEntity newbot=BotJpaEntity.builder()
                    .url(bot.getUrl())
                    .prompt(bot.getPrompt())
                    .cualidad(bot.getCualidad())
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
        BotJpaEntity foundBot=botJpaRepository.findById(id)
                .orElseThrow();

        List<LigaJpaEntity> ligas=null;
        if(changedBot.getLigasBot()!=null){
            ligas=changedBot.getLigasBot()
                    .stream()
                    .filter(Objects::nonNull)
                    .map(ligaId -> ligaJpaAdapter.getLiga(ligaId.value()))
                    .toList();
        }


        BotJpaEntity newbot=BotJpaEntity.builder()
                .idBot(botJpaMapper.toInteger(changedBot.getIdBot()))
                .url(changedBot.getUrl())
                .cualidad(changedBot.getCualidad())
                .imagen(changedBot.getImagen())
                .prompt(changedBot.getPrompt())
                .usuario(
                        usuarioJpaAdapter.getUser(botJpaMapper.toInteger(changedBot.getUsuario()))
                                .orElse(foundBot.getUsuario())
                )
                .nombre(changedBot.getNombre())
                .ligasBot(ligas)
                .build();


        return botJpaMapper.toDomain(
                botJpaRepository.save(
                        botJpaMapper.updateBot(newbot,foundBot)
                )
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
