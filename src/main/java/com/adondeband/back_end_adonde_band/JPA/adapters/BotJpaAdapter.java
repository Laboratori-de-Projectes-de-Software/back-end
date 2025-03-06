package com.adondeband.back_end_adonde_band.JPA.adapters;

import com.adondeband.back_end_adonde_band.JPA.mappers.BotMapper;
import com.adondeband.back_end_adonde_band.JPA.repositories.BotJpaRepository;
import com.adondeband.back_end_adonde_band.api.dominio.modelos.Bot;
import com.adondeband.back_end_adonde_band.api.dominio.puertos.BotPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BotJpaAdapter implements BotPort {

    private final BotJpaRepository botJpaRepository;

    private final BotMapper botMapper;

    public BotJpaAdapter(final BotJpaRepository botJpaRepository, final BotMapper botMapper) {
        this.botJpaRepository = botJpaRepository;
        this.botMapper = botMapper;
    }

    @Override
    public Bot save(Bot Bot) {
        return botMapper.toDomain(
                botJpaRepository.save(
                        botMapper.toEntity(Bot)));
    }

    @Override
    public List<Bot> findByNombre(String s) {
        return botJpaRepository.findByNombre(s)
                .stream()
                .map(botMapper::toDomain)
                .collect(Collectors.toList());
    }
}
