package com.example.demo.adapters.out.persistence.Bot;

import com.example.demo.adapters.out.persistence.League.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de Spring Data que proporciona métodos CRUD para BotEntity.
 */
@Repository
public interface SpringDataBotRepository extends JpaRepository<BotEntity, Integer> {
    // Aquí puedes definir métodos personalizados si los necesitas.
    List<BotEntity> findByUserId(Integer userId);
}
