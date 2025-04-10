package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.LogInUserDto;
import com.example.back_end_eing.dto.RegisterUserDto;
import com.example.back_end_eing.dto.UserResponseDTO;
import com.example.back_end_eing.exceptions.EmailAlreadyExistsException;
import com.example.back_end_eing.exceptions.UserAlreadyExistsException;
import com.example.back_end_eing.exceptions.UserNameNotFoundException;
import com.example.back_end_eing.exceptions.UserNotFoundException;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.services.JwtService;
import com.example.back_end_eing.services.LogInService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LogInServiceImpl implements LogInService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public UserResponseDTO signUp(RegisterUserDto registerUserDto){
        if(usuarioRepository.findByEmail(registerUserDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException();
        }
        if (usuarioRepository.findByNombreUsuario(registerUserDto.getNombreUsuario()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        // Encriptar la contraseña antes de guardarla
        String hashedPassword = passwordEncoder.encode(registerUserDto.getPassword());

        Usuario usuario = new Usuario(registerUserDto.getNombreUsuario(), registerUserDto.getEmail(), hashedPassword, null);
        Usuario savedUsuario = usuarioRepository.save(usuario);

        return UserResponseDTO.builder()
                .token(jwtService.generateToken(usuario))
                .expiresIn(null)
                .user(savedUsuario.getNombreUsuario())
                .userId(savedUsuario.getId().intValue()).build();
    }

    @Override
    public UserResponseDTO logIn(LogInUserDto logInUserDto) {
        // Buscar usuario en la base de datos por nombre de usuario
        Usuario usuario = usuarioRepository.findByNombreUsuario(logInUserDto.getNombreUsuario())
                .orElseThrow(() -> new UserNameNotFoundException(logInUserDto.getNombreUsuario()));

        // Comparar la contraseña ingresada con la almacenada
        if (!passwordEncoder.matches(logInUserDto.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        // Generar el token JWT
        return UserResponseDTO.builder()
                .token(jwtService.generateToken(usuario))
                .expiresIn(null)
                .user(usuario.getNombreUsuario())
                .userId(usuario.getId().intValue()).build();
    }
}