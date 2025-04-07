package com.example.demo.adapters.out.persistence.Bot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de Spring Data que proporciona métodos CRUD para BotEntity.
 */
@Repository
public interface SpringDataBotRepository extends JpaRepository<BotEntity, Long> {
    // Aquí puedes definir métodos personalizados si los necesitas.
}
