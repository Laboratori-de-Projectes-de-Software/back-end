package uib.lab.api.application.mapper;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.infraestructure.entity.User;
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
        return new UserDomain(entity.getId(), entity.getMail(), entity.getUsername(), entity.getPassword(), true, entity.getRoles());
    }

    @Override
    public User toEntity(UserDomain user){
        if (user == null) {
            return null;
        }
        User entity = new User();
        entity.setId(user.getId());
        entity.setMail(user.getMail());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEnabled(true);
        entity.setRoles(user.getRoles());
        return entity;
    }
}
