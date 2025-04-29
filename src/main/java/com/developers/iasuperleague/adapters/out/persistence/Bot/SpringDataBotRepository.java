package com.developers.iasuperleague.adapters.out.persistence.Bot;

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
