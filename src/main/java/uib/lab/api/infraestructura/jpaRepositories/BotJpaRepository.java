package uib.lab.api.infraestructura.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uib.lab.api.domain.entity.Bot;
import uib.lab.api.domain.entity.User;
import java.util.List;

public interface BotJpaRepository extends JpaRepository<Bot, Long> {
    List<Bot> findByUser(User user);
}
