package uib.lab.api.application.port;

import uib.lab.api.domain.BotDomain;
import uib.lab.api.domain.UserDomain;

import java.util.List;
import java.util.Optional;

public interface BotPort {
    Optional<BotDomain> findById(int id);
    List<BotDomain> findAllByUser(UserDomain user);
    List<BotDomain> findAll();
    BotDomain save(BotDomain bot);
}
