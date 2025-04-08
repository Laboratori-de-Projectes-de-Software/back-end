package uib.lab.api.application.mapper.interfaces;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.jpaEntity.User;

public interface UserMapper {
    //Transformamos de entity a Domain
    UserDomain toDomain(User user);

    //Transformamos de Domain a entity
    User toEntity(UserDomain user);

    User toEntity(UserDomain user, boolean b);
}
