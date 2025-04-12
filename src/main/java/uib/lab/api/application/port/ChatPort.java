package uib.lab.api.application.port;

import java.util.List;

import uib.lab.api.domain.ChatDomain;
import uib.lab.api.domain.MatchDomain;
import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.Match;

import java.util.Optional;

public interface ChatPort {
    //Todo
    List<ChatDomain> findAllByMatch(MatchDomain match);
    List<ChatDomain> findAllChats();
}
