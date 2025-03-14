package com.alia.back_end_service.spring_security.user;

import com.alia.back_end_service.jpa.user.UserEntity;
import com.alia.back_end_service.jpa.user.UserJpaRepository;
import com.alia.back_end_service.jpa.user.UserMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {

    private final UserJpaRepository usuarioRepository;
    private final UserMapper userMapper;

    public UserSecurityServiceImpl(UserJpaRepository usuarioRepository, UserMapper userMapper) {
        this.usuarioRepository = usuarioRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserSecurityAdapter loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UserSecurityAdapter(userMapper.toDomain(usuario));
    }
}
