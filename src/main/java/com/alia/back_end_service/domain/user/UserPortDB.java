package com.alia.back_end_service.domain.user;

import java.util.Optional;

public interface UserPortDB {

    //Gets and Find
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    //Create
    User saveUser(User user);

    //Delete
    void deleteUser(String username);
}
