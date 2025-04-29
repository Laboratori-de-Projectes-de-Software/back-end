package uib.lab.api.application.mapper.interfaces;

import uib.lab.api.application.dto.chat.ChatDTO;
import uib.lab.api.domain.ChatDomain;
import uib.lab.api.infraestructure.jpaEntity.Chat;

public interface ChatMapper {
    ChatDomain toDomain(Chat chat);
    ChatDomain toDomain(ChatDTO chat);

    Chat toEntity(ChatDomain chat);


}
