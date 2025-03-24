package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.domain.user.ports.UserPortAPI;
import com.alia.generated.api.UsersApi;
import com.alia.generated.api.UsersApiDelegate;
import com.alia.generated.model.UserLogin;
import com.alia.generated.model.UserRegister;
import com.alia.generated.model.UsersLoginPost200Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserApiDelegateImpl implements UsersApiDelegate {
    private final UserPortAPI userPortAPI;
    @Override
    public ResponseEntity<UsersLoginPost200Response> usersLoginPost(UserLogin userLogin) {
        LoginDTO loginDTO = new LoginDTO(userLogin.getUsername(), userLogin.getPassword());
        UserDTOGet authenticatedUser = userPortAPI.loginUser(loginDTO);
        UsersLoginPost200Response usersLoginPost200Response = new UsersLoginPost200Response();
        usersLoginPost200Response.token("Hola");
        return ResponseEntity.status(HttpStatus.OK).body(usersLoginPost200Response);
    }

    @Override
    public ResponseEntity<Void> usersRegisterPost(UserRegister userRegister) {
        System.out.printf("Registrando el usuario %s\n", userRegister.getUsername());
        UserDTOPost user = new UserDTOPost(userRegister.getUsername(),userRegister.getEmail(),userRegister.getPassword());
        UserDTOGet registeredUser = userPortAPI.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
