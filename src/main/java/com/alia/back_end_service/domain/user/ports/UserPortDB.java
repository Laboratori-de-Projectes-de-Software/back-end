package com.alia.back_end_service.domain.user.ports;

import com.alia.back_end_service.domain.user.User;

import java.util.Optional;

public interface UserPortDB {

    //Gets and Find
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    //Create
    User save(User user);

    //Delete
    void delete(String username);
}
