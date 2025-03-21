package uib.lab.api.entity.jpa_user;

import uib.lab.api.domain.UserDomain;
import uib.lab.api.domain.UserPort;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserJpaAdapter implements UserPort {
    private final UserJpaRepository repo;
    private final UserMapper userMapper;

    public UserJpaAdapter(final UserJpaRepository repo, final UserMapper userMapper){
        this.repo = repo; //Repositorio
        this.userMapper = userMapper; //Mapper para cambiar entre User (dominio) <-> User (entidad)
    }

    /*
     * Guarda un usuario dentro de la base de datos
     */
    @Override
    public UserDomain save(UserDomain user) {
        return userMapper.toDomain(
                repo.save(
                        userMapper.toEntity(user)));
    }

    /*
     * Recoge todos los usuarios que se encuentran dentro de la base de datos
     */
     @Override
    public List<UserDomain> findAll() {
        return repo.findAll()
                   .stream()
                   .map(userMapper::toDomain)
                   .collect(Collectors.toList());
    }
}
