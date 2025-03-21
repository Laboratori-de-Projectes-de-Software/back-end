package uib.lab.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uib.lab.api.entity.Bot;
import uib.lab.api.entity.User;
import java.util.List;

public interface BotRepository extends JpaRepository<Bot, Long> {
    List<Bot> findByUser(User user);
}
