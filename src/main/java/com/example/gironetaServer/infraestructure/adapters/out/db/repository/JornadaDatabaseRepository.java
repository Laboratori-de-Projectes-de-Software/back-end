package com.example.gironetaServer.infraestructure.adapters.out.db.repository;

import com.example.gironetaServer.application.ports.JornadaRepository;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.JornadaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JornadaDatabaseRepository implements JornadaRepository {

    private final JornadaJpaRepository jornadaJpaRepository;

    public JornadaDatabaseRepository(JornadaJpaRepository jornadaJpaRepository) {
        this.jornadaJpaRepository = jornadaJpaRepository;
    }

    @Override
    public Optional<JornadaEntity> findById(Long id) {
        return jornadaJpaRepository.findById(id);
    }

    @Override
    public List<JornadaEntity> findByLigaId(Long ligaId) {
        return jornadaJpaRepository.findByLigaId(ligaId);
    }

    @Override
    public JornadaEntity save(JornadaEntity jornada) {
        return jornadaJpaRepository.save(jornada);
    }

    @Override
    public void deleteById(Long id) {
        jornadaJpaRepository.deleteById(id);
    }
}
