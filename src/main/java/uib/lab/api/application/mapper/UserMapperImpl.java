package uib.lab.api.application.mapper;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    /*
     * Métodos para cambiar entre Entity <-> Domain
     */
    @Override
    public UserDomain toDomain(User entity){
        if (entity == null) {
            return null;
        }
        return new UserDomain(entity.getId(), entity.getUsername(), entity.getName(), entity.getPassword(), true, entity.getRoles());
    }

    @Override
    public User toEntity(UserDomain user){
        if (user == null) {
            return null;
        }
        User entity = new User();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
        entity.setEnabled(true);
        entity.setRoles(user.getRoles());
        return entity;
    }
}
