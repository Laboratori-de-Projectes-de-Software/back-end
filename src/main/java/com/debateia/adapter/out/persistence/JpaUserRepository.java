package com.debateia.adapter.out.persistence;

import com.debateia.application.ports.out.persistence.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kjorda
 */
public class JpaUserRepository implements UserRepository {
    public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    
    }
    
}
