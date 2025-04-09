package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api_model.UserDTOLogin;
import com.alia.back_end_service.api_model.UserDTORegister;
import com.alia.back_end_service.api_model.UserResponseDTO;
import com.alia.back_end_service.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperAPIImpl implements UserMapperAPI {
    @Override
    public User toDomainRegister(UserDTORegister userRegister) {
        User user = new User();
        user.setUsername(userRegister.getUser());
        user.setPassword(userRegister.getPassword());
        user.setMail(userRegister.getMail());
        return user;
    }

    @Override
    public User toDomainLogin(UserDTOLogin userLogin) {
        User user = new User();
        user.setUsername(userLogin.getUser());
        user.setPassword(userLogin.getPassword());
        return user;
    }

    @Override
    public UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setUser(user.getUsername());
        return userResponseDTO;
    }
}
