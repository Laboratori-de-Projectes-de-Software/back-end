package com.adondeband.back_end_adonde_band.jpa.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotPort;
import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotJpaAdapter implements BotPort {

    private final BotJpaRepository botJpaRepository;
    private final BotJpaMapper botJpaMapper;
    private final UsuarioJpaMapper usuarioJpaMapper;

    public BotJpaAdapter(final BotJpaRepository botJpaRepository, final BotJpaMapper botJpaMapper, final UsuarioJpaMapper usuarioJpaMapper) {
        this.botJpaRepository = botJpaRepository;
        this.botJpaMapper = botJpaMapper;
        this.usuarioJpaMapper = usuarioJpaMapper;
    }

    @Override
    public Bot save(Bot bot) {
        /*
        // DEBUG
        BotEntity botEntity = botJpaMapper.toEntity(bot);
        BotEntity saved = botJpaRepository.save(botEntity);
        Bot botR = botJpaMapper.toDomain(saved);
        return botR;
         */

        return botJpaMapper.toDomain(
                botJpaRepository.save(
                        botJpaMapper.toEntity(bot)));
    }

    @Override
    public List<Bot> findByNombre(String s) {
        return botJpaRepository.findByNombre(s)
                .stream()
                .map(botJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Bot> findAll() {
        return botJpaRepository.findAll()
                .stream()
                .map(botJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Bot> findBotsUsuario(UsuarioId userId) {
        //TODO
        return List.of();
    }

    @Override
    public List<Bot> findByUsuario(Usuario usuario) {
        return botJpaRepository.findByUsuario(usuarioJpaMapper.toEntity(usuario))
                .stream()
                .map(botJpaMapper::toDomain)
                .collect(Collectors.toList());

    }


}
