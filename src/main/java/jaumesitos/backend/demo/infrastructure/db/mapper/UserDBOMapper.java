package jaumesitos.backend.demo.infrastructure.db.mapper;

import org.mapstruct.Mapper;
import jaumesitos.backend.demo.infrastructure.db.dbo.UserDBO;
import jaumesitos.backend.demo.domain.User;

@Mapper(componentModel = "spring")
public interface UserDBOMapper {
    UserDBO toDBO(User user);
    User toUser(UserDBO userDBO);
}
