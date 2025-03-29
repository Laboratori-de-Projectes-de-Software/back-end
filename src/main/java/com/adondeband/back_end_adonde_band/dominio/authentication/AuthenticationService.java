package com.adondeband.back_end_adonde_band.dominio.authentication;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {


    public Usuario signup(Usuario input);

    public Usuario authenticate(Usuario input);
}
