package com.adondeband.back_end_adonde_band.JPA.adapters;

import com.adondeband.back_end_adonde_band.JPA.mappers.BotJpaMapper;
import com.adondeband.back_end_adonde_band.JPA.repositories.BotJpaRepository;
import com.adondeband.back_end_adonde_band.dominio.modelos.Bot;
import com.adondeband.back_end_adonde_band.dominio.puertos.out.BotPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotJpaAdapter implements BotPort {

    private final BotJpaRepository botJpaRepository;

    private final BotJpaMapper botJpaMapper;

    public BotJpaAdapter(final BotJpaRepository botJpaRepository, final BotJpaMapper botJpaMapper) {
        this.botJpaRepository = botJpaRepository;
        this.botJpaMapper = botJpaMapper;
    }

    @Override
    public Bot save(Bot bot) {
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
}
