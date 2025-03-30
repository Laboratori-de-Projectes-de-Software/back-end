package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api.UsersApiDelegate;
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
public class UserApiDelegateImpl implements UsersApiDelegate {
    private final UserRegistrationPortAPI userRegistrationPortAPI;
    private final UserLoginPortAPI userLoginPortAPI;
    private final UserGetPortAPI userGetPortAPI;
    private final GetAllUserBotsPortAPI getAllUserBotsPortAPI;
    private final GetAllUserLeaugesPortAPI getAllUserLeaugesPortAPI;
    private final UserMapperAPI userMapperPortAPI;
    private final BotMapperAPI botMapperPortAPI;
    private final LeagueMapperAPI leagueMapperPortAPI;

    @Override
    public ResponseEntity<UsersLoginPost200Response> usersLoginPost(UserLogin userLogin) {
        return ResponseEntity.status(HttpStatus.OK).body(new UsersLoginPost200Response().token(userLoginPortAPI.login(userLogin.getUsername(), userLogin.getPassword())));
    }

    @Override
    public ResponseEntity<Void> usersRegisterPost(UserRegister userRegister) {
        userRegistrationPortAPI.registerUser(userMapperPortAPI.toDomainRegister(userRegister));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<BotReturn>> usersIdBotsGet(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(getAllUserBotsPortAPI.getAllUserBots(id)
                .stream()
                .map(botMapperPortAPI::toApiResponse)
                .toList());
    }

    @Override
    public ResponseEntity<UserResponse> usersIdGet(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userMapperPortAPI.toApiResponse(userGetPortAPI.getUser(id)));
    }

    @Override
    public ResponseEntity<List<LeagueResponse>> usersIdLeaguesGet(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(getAllUserLeaugesPortAPI.GetAllUserLeauges(id)
                .stream()
                .map(leagueMapperPortAPI::toApiResponse)
                .toList());
    }


}
