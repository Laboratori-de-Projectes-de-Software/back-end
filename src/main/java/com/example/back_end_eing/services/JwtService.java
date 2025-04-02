package com.example.back_end_eing.services;

import com.example.back_end_eing.models.Usuario;
import org.springframework.security.core.userdetails.UserDetails;


public interface JwtService {
    public String generateToken(Usuario usuario);


    String extractUsername(String jwt);

    boolean isTokenValid(String jwt, UserDetails userDetails);
}
