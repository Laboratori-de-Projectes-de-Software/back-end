package uib.lab.api.infraestructure.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uib.lab.api.infraestructure.jpaEntity.Round;

public interface RoundJpaRepository extends JpaRepository<Round, Integer> {
}
