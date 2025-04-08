package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uib.lab.api.infraestructure.jpaEntity.Bot;
import uib.lab.api.infraestructure.jpaEntity.User;
import java.util.List;
import java.util.Optional;

public interface BotJpaRepository extends JpaRepository<Bot, Integer> {
    List<Bot> findAllByUser(User user);
    Optional<Bot> findBotById(int id);
}
