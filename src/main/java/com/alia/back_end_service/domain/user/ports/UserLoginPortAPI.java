package com.alia.back_end_service.domain.user.ports;


public interface UserLoginPortAPI {
    String login(String username, String password);
}
