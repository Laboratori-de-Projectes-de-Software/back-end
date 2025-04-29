package com.alia.back_end_service.domain.user.ports;

import com.alia.back_end_service.domain.user.User;

public interface TokenProviderPort {
    String generateToken(User user);
}
