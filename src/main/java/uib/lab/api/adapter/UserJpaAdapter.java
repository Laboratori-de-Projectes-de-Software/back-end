package uib.lab.api.adapter;


import uib.lab.api.domain.UserDomain;
import uib.lab.api.domain.UserPort;
import uib.lab.api.entity.User;
import uib.lab.api.mapper.UserMapper;
import uib.lab.api.repository.UserJpaRepository;

import java.util.Optional;

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

    /*
     * Buscamos un usuario usando su identificador
     */
    @Override
    public Optional<UserDomain> findById(Long id) {
        return repo.findById(id).map(userMapper::toDomain);
    }

    /*
     * Método que actualiza un usuario
     */
    @Override
    public UserDomain update(UserDomain user){
        //Buscamos su id
        User entity = repo.findById(user.getId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        //Actualizamos los datos
        entity.setUsername(user.getUsername());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
  
        //Lo guardamos en vase de datos
        return userMapper.toDomain(repo.save(entity));
    }
}
