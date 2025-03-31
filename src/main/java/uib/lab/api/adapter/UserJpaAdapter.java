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
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserJpaAdapter(final UserJpaRepository userJpaRepository, final UserMapper userMapper){
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDomain> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(userMapper::toDomain);
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        User userEntity = userMapper.toEntity(userDomain);
        User savedUser = userJpaRepository.save(userEntity);
        System.out.println("Usuario guardado con ID: " + savedUser.getId());
        return userMapper.toDomain(savedUser);
    }

     @Override
    public List<UserDomain> findAll() {
        return userJpaRepository.findAll()
                   .stream()
                   .map(userMapper::toDomain)
                   .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        return userJpaRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public UserDomain update(UserDomain user){
        User entity = userJpaRepository.findById(user.getId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        entity.setUsername(user.getUsername());
        entity.setName(user.getName());
        entity.setPassword(user.getPassword());
  
        return userMapper.toDomain(userJpaRepository.save(entity));
    }
}
