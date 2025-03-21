package uib.lab.api.entity.jpa_user;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.entity.User;

public interface UserMapper {
    UserDomain toDomain(User user);

    uib.lab.api.entity.User toEntity(UserDomain user);
}
