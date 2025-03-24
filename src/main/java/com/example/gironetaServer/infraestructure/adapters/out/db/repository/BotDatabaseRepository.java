package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.BotRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.BotEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class BotDatabaseRepository implements BotRepository {

    private final BotJpaRepository botJpaRepository;

    public BotDatabaseRepository(BotJpaRepository botJpaRepository) {
        this.botJpaRepository = botJpaRepository;
    }

    @Override
    public Optional<BotEntity> findById(Long id) {
        return botJpaRepository.findById(id);
    }

    @Override
    public List<BotEntity> findByUsuarioCorreo(String correo) {
        return botJpaRepository.findByUsuarioEmail(correo);
    }

    @Override
    public BotEntity save(BotEntity bot) {
        return botJpaRepository.save(bot);
    }

    @Override
    public void deleteById(Long id) {
        botJpaRepository.deleteById(id);
    }
}
