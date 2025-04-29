package com.alia.back_end_service.domain.user.usecases;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.exceptions.UserNotFoundException;
import com.alia.back_end_service.domain.user.ports.UserGetPortAPI;
import com.alia.back_end_service.domain.user.ports.UserPortDB;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetUserUseCase implements UserGetPortAPI {
    private final UserPortDB userPortDB;
    @Override
    public User getUser(String username) {
        return userPortDB.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }
}
