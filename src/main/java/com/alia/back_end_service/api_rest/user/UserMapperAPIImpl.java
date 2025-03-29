package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api_model.UserRegister;
import com.alia.back_end_service.api_model.UserResponse;
import com.alia.back_end_service.domain.user.Role;
import com.alia.back_end_service.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperAPIImpl implements UserMapperAPI {
    @Override
    public User toDomainRegister(UserRegister userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setPassword(userRegister.getPassword());
        user.setMail(userRegister.getEmail());
        user.setRole(Role.USER);

        return user;
    }

    @Override
    public UserResponse toApiResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getMail());
        return userResponse;
    }
}
