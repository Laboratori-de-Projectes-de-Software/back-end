package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.domain.user.usecases.LoginUserUseCase;
import com.alia.back_end_service.domain.user.usecases.RegisterUserUseCase;
import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.ports.UserPortAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.alia.back_end_service.domain.user.Role;


@Component
@RequiredArgsConstructor
public class UserAPIAdapter implements UserPortAPI {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @Override
    public UserDTOGet registerUser(UserDTOPost userDTOPost) {
        User user = new User(userDTOPost.username(), userDTOPost.mail(), userDTOPost.password(), userDTOPost.photo(), Role.USER);
        User savedUser = registerUserUseCase.execute(user);
        return new UserDTOGet(savedUser);
    }

    @Override
    public UserDTOGet loginUser(LoginDTO loginDTO) {
        User authenticatedUser = loginUserUseCase.execute(loginDTO.username(), loginDTO.password());
        return new UserDTOGet(authenticatedUser);
    }
}
