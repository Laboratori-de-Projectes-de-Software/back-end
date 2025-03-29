package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api.UsersApiDelegate;
import com.alia.back_end_service.api_model.*;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.user.Role;
import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.ports.GetAllUserBotsPortAPI;
import com.alia.back_end_service.domain.user.ports.UserGetPortAPI;
import com.alia.back_end_service.domain.user.ports.UserLoginPortAPI;
import com.alia.back_end_service.domain.user.ports.UserRegistrationPortAPI;
import com.alia.back_end_service.domain.user.usecases.LoginUserUseCase;
import com.alia.back_end_service.domain.user.usecases.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserApiDelegateImpl implements UsersApiDelegate {
    private final UserRegistrationPortAPI userRegistrationPortAPI;
    private final UserLoginPortAPI userLoginPortAPI;
    private final UserGetPortAPI userGetPortAPI;
    private final GetAllUserBotsPortAPI getAllUserBotsPortAPI;

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

    @Override
    public ResponseEntity<List<BotReturn>> usersIdBotsGet(String id) {
        List<Bot> bots = getAllUserBotsPortAPI.getAllUserBots(id);
        List<BotReturn> botReturns = new ArrayList<>();
        BotReturn botReturn;
        for (Bot bot : bots) {
             botReturn = new BotReturn();
             botReturn.setName(bot.getName());
             botReturn.setDescription(bot.getDescription());
             botReturns.add(botReturn);
        }
        return ResponseEntity.status(HttpStatus.OK).body(botReturns);
    }

    @Override
    public ResponseEntity<UserResponse> usersIdGet(String id) {
        User user = userGetPortAPI.getUser(id);
        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getMail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<List<LeagueResponse>> usersIdLeaguesGet(String id) {
        return UsersApiDelegate.super.usersIdLeaguesGet(id);
    }


}
