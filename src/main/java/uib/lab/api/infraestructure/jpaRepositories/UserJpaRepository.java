package uib.lab.api.infraestructure.jpaRepositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uib.lab.api.infraestructure.jpaEntity.User;


@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);

    Optional<User> findById(int id);
}
