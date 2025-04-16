package com.developers.iasuperleague.adapters.in.web;

import com.developers.iasuperleague.application.port.in.UserUseCase;
import com.developers.iasuperleague.application.service.JwtService;
import com.developers.iasuperleague.domain.model.User;
import com.developers.iasuperleague.dtos.UserDTOLogin;
import com.developers.iasuperleague.dtos.UserDTORegister;
import com.developers.iasuperleague.dtos.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@RequestMapping("/api/v0/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final UserUseCase userUseCase;

    public AuthenticationController(JwtService jwtService, UserUseCase userUseCase) {
        this.jwtService = jwtService;
        this.userUseCase = userUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTORegister> register(@RequestBody UserDTORegister userDTORegister) {
        UserDTORegister registeredUser = userUseCase.register(userDTORegister);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserDTOLogin userDTOLogin) {

        UserDTOLogin authenticatedUser = userUseCase.login(userDTOLogin);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Obtén la hora actual con la zona horaria del sistema
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime expirationTime = currentTime.plusNanos(jwtService.getExpirationTime());
        Date expiresInDate = Date.from(expirationTime.toInstant());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer userId = userDetails.getId();

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setToken(jwtToken);
        userResponseDTO.setExpiresIn(new Date((new Date()).getTime() + jwtService.getExpirationTime()*3));  // Fecha actual
        userResponseDTO.setUser(userDTOLogin.getUsername());
        userResponseDTO.setUserId(userId);

        return ResponseEntity.ok(userResponseDTO);
    }
}
