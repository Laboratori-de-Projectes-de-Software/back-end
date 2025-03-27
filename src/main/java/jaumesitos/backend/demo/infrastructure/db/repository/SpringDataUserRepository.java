package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.infrastructure.db.dbo.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SpringDataUserRepository {
    Optional<UserDBO> findByEmail(String email);
}
