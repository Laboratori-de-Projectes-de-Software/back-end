package uib.lab.api.entity.jpa_user;

import uib.lab.api.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toDomain(uib.lab.api.entity.User entity){
        return new User(entity.getId(), entity.getUsername(), entity.getName(), entity.getPassword());
    }

    @Override
    public uib.lab.api.entity.User toEntity(User user){
        return new uib.lab.api.entity.User(user.getId(), user.getUsername(), user.getName(), user.getPassword());
    }
    
}

