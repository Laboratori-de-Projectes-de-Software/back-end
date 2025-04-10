package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api_model.UserDTOLogin;
import com.alia.back_end_service.api_model.UserDTORegister;
import com.alia.back_end_service.domain.user.User;

public interface UserMapperAPI {
    User toDomainRegister(UserDTORegister userRegister);
    User toDomainLogin(UserDTOLogin userLogin);
}
