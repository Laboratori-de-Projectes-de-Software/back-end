package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import uib.lab.api.infraestructure.jpaEntity.Chat;
import uib.lab.api.infraestructure.jpaEntity.League;
import uib.lab.api.infraestructure.jpaEntity.Match;
import uib.lab.api.infraestructure.jpaEntity.User;
import java.util.List;
import java.util.Optional;

public interface ChatJpaRepository extends JpaRepository<Chat, Integer> {
    Optional<Chat> findChatById(int id);
    List<Chat> findAllByMatch(Match match);
}
