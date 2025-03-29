package com.adondeband.back_end_adonde_band.API.authentication;

import com.adondeband.back_end_adonde_band.dominio.authentication.AuthenticationImpl;
import com.adondeband.back_end_adonde_band.dominio.authentication.AuthenticationService;
import com.adondeband.back_end_adonde_band.dominio.authentication.JwtService;
import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final AuthenticationDtoMapper authenticationDtoMapper;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationImpl authenticationService, AuthenticationDtoMapper authenticationDtoMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.authenticationDtoMapper = new AuthenticationDtoMapper();
    }

    @PostMapping("/signup")
    public ResponseEntity<Usuario> register(@RequestBody RegisterUserDto registerUserDto) {
        Usuario registeredUser = authenticationService.signup( authenticationDtoMapper.toDomain(registerUserDto) );

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(authenticationDtoMapper.toDomain(loginUserDto));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                authenticatedUser.getNombre().value(),
                authenticatedUser.getContrasena(),
                new ArrayList<>()
        );

        String jwtToken = jwtService.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
