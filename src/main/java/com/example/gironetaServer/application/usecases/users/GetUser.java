package com.example.gironetaServer.application.usecases.users;

import com.example.gironetaServer.domain.User;

import java.util.List;

public interface GetUser {
    List<User> getUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);
}
