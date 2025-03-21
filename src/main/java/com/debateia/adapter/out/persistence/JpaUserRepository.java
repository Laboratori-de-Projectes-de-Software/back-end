package com.debateia.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kjorda
 */
public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {
    
}
