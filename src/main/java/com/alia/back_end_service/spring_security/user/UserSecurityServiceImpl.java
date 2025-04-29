package com.alia.back_end_service.spring_security.user;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import com.alia.back_end_service.jpa.user.UserEntity;
import com.alia.back_end_service.jpa.user.UserJpaAdapter;
import com.alia.back_end_service.jpa.user.UserJpaRepository;
import com.alia.back_end_service.jpa.user.UserMapper;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityServiceImpl implements UserDetailsService{

    private final UserJpaAdapter usuarioRepository;
    private final UserMapper userMapper;

    public UserSecurityServiceImpl(UserJpaAdapter usuarioRepository, UserMapper userMapper) {
        this.usuarioRepository = usuarioRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserSecurityAdapter loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UserSecurityAdapter(usuario);
    }

}
