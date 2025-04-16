package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api_model.UserDTOLogin;
import com.alia.back_end_service.api_model.UserDTORegister;
import com.alia.back_end_service.domain.user.Role;
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
        user.setRole(Role.USER);

        return user;
    }

    @Override
    public User toDomainLogin(UserDTOLogin userLogin) {
        User user = new User();
        user.setUsername(userLogin.getUser());
        user.setPassword(userLogin.getPassword());
        user.setMail(null);
        user.setRole(Role.USER);

        return user;
    }
}
