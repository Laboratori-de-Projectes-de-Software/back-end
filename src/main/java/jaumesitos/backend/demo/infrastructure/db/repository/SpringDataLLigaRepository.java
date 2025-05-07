package jaumesitos.backend.demo.infrastructure.db.repository;

//Interfície per interactuar amb la base de dades
//Per definir

import jaumesitos.backend.demo.infrastructure.db.dbo.ParticipationDBO;
import jaumesitos.backend.demo.infrastructure.db.dbo.LeagueDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataLLigaRepository extends JpaRepository<LeagueDBO, Integer> {
    List<LeagueDBO> findByUserId(Integer owner_id);
}
