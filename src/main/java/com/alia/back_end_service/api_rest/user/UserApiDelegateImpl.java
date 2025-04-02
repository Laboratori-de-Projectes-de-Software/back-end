package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api.AuthApiDelegate;
import com.alia.back_end_service.api_model.*;
import com.alia.back_end_service.api_rest.bot.BotMapperAPI;
import com.alia.back_end_service.api_rest.league.LeagueMapperAPI;
import com.alia.back_end_service.domain.user.ports.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserApiDelegateImpl implements AuthApiDelegate {
    private final UserRegistrationPortAPI userRegistrationPortAPI;
    private final UserLoginPortAPI userLoginPortAPI;
    private final UserMapperAPI userMapperPortAPI;


    @Override
    public ResponseEntity<AuthLoginPost200Response> authLoginPost(UserDTOLogin userDTOLogin) {
        return ResponseEntity.status(HttpStatus.OK).body(new AuthLoginPost200Response().token(userLoginPortAPI.login(userMapperPortAPI.toDomainLogin(userDTOLogin))));
    }

    @Override
    public ResponseEntity<Void> authRegisterPost(UserDTORegister userDTORegister) {
        userRegistrationPortAPI.registerUser(userMapperPortAPI.toDomainRegister(userDTORegister));
        return ResponseEntity.ok().build();
    }
}
