package uib.lab.api.domain.repository;

import uib.lab.api.domain.entity.Bot;
import uib.lab.api.domain.entity.User;

import java.util.List;

public interface BotRepository {
    List<Bot> findByUser(User user);
}
