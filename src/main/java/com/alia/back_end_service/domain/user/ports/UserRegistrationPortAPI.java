package com.alia.back_end_service.domain.user.ports;

import com.alia.back_end_service.domain.user.User;

public interface UserRegistrationPortAPI {
    void registerUser(User user);
}
