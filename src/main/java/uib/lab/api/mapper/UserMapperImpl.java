package uib.lab.api.mapper;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    /*
     * Métodos para cambiar entre Entity <-> Domain
     */
    @Override
    public UserDomain toDomain(User entity){
        return new UserDomain(entity.getId(), entity.getUsername(), entity.getName(), entity.getPassword());
    }

    @Override
    public User toEntity(UserDomain user){
        return new User(user.getId(), user.getUsername(), user.getName(), user.getPassword());
    }
    
}

