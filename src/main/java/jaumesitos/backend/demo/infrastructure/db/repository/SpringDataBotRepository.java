package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.infrastructure.db.dbo.BotDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataBotRepository extends JpaRepository<BotDBO, Integer> {
    List<BotDBO> findAllByOwnerId(int ownerId);
}
