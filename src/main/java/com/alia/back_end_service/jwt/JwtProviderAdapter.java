package com.alia.back_end_service.jwt;

import com.alia.back_end_service.domain.user.User;
import com.alia.back_end_service.domain.user.ports.TokenProviderPort;
import com.alia.back_end_service.spring_security.user.UserSecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProviderAdapter implements TokenProviderPort {
    private final JwtProvider jwtProvider;

    @Override
    public String generateToken(User user) {
        return jwtProvider.generateToken(new UserSecurityAdapter(user));
    }
}