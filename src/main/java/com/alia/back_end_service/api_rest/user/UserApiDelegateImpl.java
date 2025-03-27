package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api.UsersApiDelegate;
import com.alia.back_end_service.api_model.UserLogin;
import com.alia.back_end_service.api_model.UserRegister;
import com.alia.back_end_service.api_model.UsersLoginPost200Response;
import com.alia.back_end_service.domain.user.Role;
import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.ports.UserLoginPortAPI;
import com.alia.back_end_service.domain.user.ports.UserRegistrationPortAPI;
import com.alia.back_end_service.domain.user.usecases.LoginUserUseCase;
import com.alia.back_end_service.domain.user.usecases.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserApiDelegateImpl implements UsersApiDelegate {
    private final UserRegistrationPortAPI userRegistrationPortAPI;
    private final UserLoginPortAPI userLoginPortAPI;

    @Override
    public ResponseEntity<UsersLoginPost200Response> usersLoginPost(UserLogin userLogin) {
        String token = userLoginPortAPI.login(userLogin.getUsername(), userLogin.getPassword());
        UsersLoginPost200Response response = new UsersLoginPost200Response().token(token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> usersRegisterPost(UserRegister userRegister) {
        //System.out.printf("Registrando el usuario %s\n", userRegister.getUsername());
        User user = new User(userRegister.getUsername(), userRegister.getEmail(), userRegister.getPassword(), Role.USER, Collections.emptyList());
        User savedUser = userRegistrationPortAPI.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
