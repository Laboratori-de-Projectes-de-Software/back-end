package com.example.gironetaServer.infraestructure.adapters.in.controllers;


import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.LoginUserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.RegisterUserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.UserMapper;
import com.example.gironetaServer.infraestructure.adapters.out.db.entities.UserEntity;
import com.example.gironetaServer.infraestructure.adapters.security.AuthenticationService;
import com.example.gironetaServer.infraestructure.adapters.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        UserDto userDto = UserMapper.toUserDto(registeredUser);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}