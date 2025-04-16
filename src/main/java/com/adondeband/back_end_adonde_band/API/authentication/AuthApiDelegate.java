package com.adondeband.back_end_adonde_band.API.authentication;

import com.adondeband.back_end_adonde_band.dominio.authentication.AuthenticationImpl;
import com.adondeband.back_end_adonde_band.dominio.authentication.AuthenticationService;
import com.adondeband.back_end_adonde_band.dominio.authentication.JwtService;
import com.adondeband.back_end_adonde_band.dominio.exception.UserAlreadyExistsException;
import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.adondeband.back_end_adonde_band.api.ApiApiDelegate;

import java.util.ArrayList;

//TODO ESTO NO VA; NO SE COMO COÑO FUNCIONA EL APIAPIDELEGATE
// SI ALGUIEN QUIERO TESTEAR HAY QUE CAMBIAR DE VUELTA

//@Controller
@RestController
@RequestMapping("api/v0/auth")
public class AuthApiDelegate
        //implements ApiApiDelegate
{
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private final AuthenticationDtoMapper authenticationDtoMapper;


    public AuthApiDelegate(JwtService jwtService, AuthenticationImpl authenticationService, AuthenticationDtoMapper authenticationDtoMapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.authenticationDtoMapper = authenticationDtoMapper;
    }

    @GetMapping("/test")
    public String getPath() {
        return "api/v0/auth";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        try {
            Usuario registeredUser = authenticationService.signup(authenticationDtoMapper.registerUserDtotoDomain(registerUserDto));
            return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado con éxito");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        try {
            Usuario authenticatedUser = authenticationService.authenticate(authenticationDtoMapper.loginUserDtotoDomain(loginUserDto));
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    authenticatedUser.getNombre(),
                    authenticatedUser.getContrasena(),
                    new ArrayList<>()
            );

            String jwtToken = jwtService.generateToken(userDetails);

            LoginResponse loginResponse = new LoginResponse()
                    .setToken(jwtToken)
                    .setExpiresIn(System.currentTimeMillis() + jwtService.getExpirationTime())
                    .setUser(authenticatedUser.getNombre())
                    .setUserId(authenticatedUser.getId().value());

            return ResponseEntity.ok(loginResponse);
        }catch (UsernameNotFoundException | BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
