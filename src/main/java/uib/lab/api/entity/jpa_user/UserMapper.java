package uib.lab.api.entity.jpa_user;

import uib.lab.api.domain.User;

public interface UserMapper {
    User toDomain(uib.lab.api.entity.User user);

    uib.lab.api.entity.User toEntity(User user);
}
