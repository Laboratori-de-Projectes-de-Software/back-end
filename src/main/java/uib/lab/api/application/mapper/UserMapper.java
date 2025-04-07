package uib.lab.api.application.mapper;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.entity.User;

public interface UserMapper {
    //Transformamos de entity a Domain
    UserDomain toDomain(User user);

    //Transformamos de Domain a entity
    User toEntity(UserDomain user);
}
