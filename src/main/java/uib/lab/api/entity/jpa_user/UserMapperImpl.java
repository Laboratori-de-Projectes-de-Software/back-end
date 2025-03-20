package uib.lab.api.entity.jpa_user;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDomain toDomain(User entity){
        return new UserDomain(entity.getId(), entity.getUsername(), entity.getName(), entity.getPassword());
    }

    @Override
    public User toEntity(UserDomain user){
        return new User(user.getId(), user.getUsername(), user.getName(), user.getPassword());
    }
    
}

