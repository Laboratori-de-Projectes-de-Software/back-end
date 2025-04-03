package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.BotRepository;
import com.example.gironetaServer.domain.Bot;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.BotMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BotDatabaseRepository implements BotRepository {

    private final BotJpaRepository botJpaRepository;
    private final BotMapper botMapper;  // Inyectamos BotMapper

    public BotDatabaseRepository(BotJpaRepository botJpaRepository, BotMapper botMapper) {
        this.botJpaRepository = botJpaRepository;
        this.botMapper = botMapper;
    }

    @Override
    public Optional<Bot> findById(Long id) {
        return botJpaRepository.findById(id)
                .map(botMapper::toDomain);  // Usamos botMapper en lugar del método estático
    }

    @Override
    public List<Bot> findByUsuarioCorreo(String correo) {
        return botJpaRepository.findByUsuarioEmail(correo)
                .stream()
                .map(botMapper::toDomain)  // Convertimos cada entidad a dominio
                .toList();
    }

    @Override
    public Bot save(Bot bot) {
        BotEntity botEntity = botMapper.toEntity(bot);  // Usamos botMapper
        botEntity = botJpaRepository.save(botEntity);
        return botMapper.toDomain(botEntity);  // Usamos botMapper
    }

    @Override
    public void deleteById(Long id) {
        botJpaRepository.deleteById(id);
    }

    @Override
    public List<Bot> getAllBots() {
        return botJpaRepository.findAll().stream()
                .map(botMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Bot> getBotsByOwner(Long userId) {
        return botJpaRepository.findAll().stream()
                .filter(botEntity -> botEntity.getUsuario() != null && botEntity.getUsuario().getId().equals(userId))
                .map(botMapper::toDomain)
                .collect(Collectors.toList());
    }
}