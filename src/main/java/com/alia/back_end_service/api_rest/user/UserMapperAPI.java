package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.api_model.UserRegister;
import com.alia.back_end_service.api_model.UserResponse;
import com.alia.back_end_service.domain.user.User;

public interface UserMapperAPI {
    User toDomainRegister(UserRegister userRegister);
    UserResponse toApiResponse(User user);
}
