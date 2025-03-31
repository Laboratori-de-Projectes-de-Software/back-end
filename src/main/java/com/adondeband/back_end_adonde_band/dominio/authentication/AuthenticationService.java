package com.adondeband.back_end_adonde_band.dominio.authentication;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {


    Usuario signup(Usuario input);

    Usuario authenticate(Usuario input);
}
