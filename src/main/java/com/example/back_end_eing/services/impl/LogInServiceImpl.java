package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.UserDTOLogin;
import com.example.back_end_eing.dto.UserDTORegister;
import com.example.back_end_eing.dto.UserResponseDTO;
import com.example.back_end_eing.exceptions.UserAlreadyExistsException;
import com.example.back_end_eing.exceptions.UserNameNotFoundException;
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
    public void signUp(UserDTORegister userDTORegister) {
        if (usuarioRepository.findByEmail(userDTORegister.getMail()).isPresent()) {
            throw new UserAlreadyExistsException(userDTORegister.getMail());
        }

        // Encriptar la contraseña antes de guardarla
        String hashedPassword = passwordEncoder.encode(userDTORegister.getPassword());

        Usuario usuario = new Usuario(userDTORegister.getUser(), userDTORegister.getMail(), hashedPassword, "");
        usuarioRepository.save(usuario);
    }

    @Override
    public UserResponseDTO logIn(UserDTOLogin userDTOLogin) {
        // Buscar usuario en la base de datos por nombre de usuario
        Usuario usuario = usuarioRepository.findByEmail(userDTOLogin.getMail()).orElseThrow(() -> new UserNameNotFoundException(userDTOLogin.getMail()));

        // Comparar la contraseña ingresada con la almacenada
        if (!passwordEncoder.matches(userDTOLogin.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        // Generar el token JWT
        String token = jwtService.generateToken(usuario);
        return UserResponseDTO.builder()
                .token(token)
                .user(jwtService.extractUsername(token))
                .expiresIn(jwtService.extractExpiration(token).getTime())
                .userId(Integer.parseInt(jwtService.extractId(token)))
                .build();


    }
}