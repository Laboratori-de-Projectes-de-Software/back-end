package uib.lab.api.infraestructura.JpaRepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uib.lab.api.domain.entity.User;


@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
