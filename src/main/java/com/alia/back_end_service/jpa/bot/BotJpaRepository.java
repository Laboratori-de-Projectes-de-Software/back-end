package com.alia.back_end_service.jpa.bot;

import com.alia.back_end_service.domain.bot.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BotJpaRepository extends JpaRepository<BotEntity, Integer> {
    Optional<BotEntity> findByName(String name);
    void deleteByName(String name);
}
