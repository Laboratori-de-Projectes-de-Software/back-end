package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uib.lab.api.infraestructure.entity.Bot;
import uib.lab.api.infraestructure.entity.User;
import java.util.List;

public interface BotJpaRepository extends JpaRepository<Bot, Long> {
    List<Bot> findByUser(User user);
}
