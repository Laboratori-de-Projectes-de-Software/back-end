package com.developers.iasuperleague.application.port.out;

import com.developers.iasuperleague.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User register(User user);
    User findByUsername(String username);
}