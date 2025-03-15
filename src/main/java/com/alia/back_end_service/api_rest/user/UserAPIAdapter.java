package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.domain.user.RegisterUserUseCase;
import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.UserPortAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.alia.back_end_service.domain.user.Role;


@Component
@RequiredArgsConstructor
public class UserAPIAdapter implements UserPortAPI {
    private final RegisterUserUseCase registerUserUseCase;

    @Override
    public UserDTOGet registerUser(UserDTOPost userDTOPost) {
        User user = new User(userDTOPost.username(), userDTOPost.mail(), userDTOPost.password(), userDTOPost.photo(), Role.USER);
        User savedUser = registerUserUseCase.execute(user);
        return new UserDTOGet(savedUser);
    }
}
