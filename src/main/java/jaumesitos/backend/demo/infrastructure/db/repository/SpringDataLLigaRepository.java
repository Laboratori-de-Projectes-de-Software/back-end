package jaumesitos.backend.demo.infrastructure.db.repository;

//Interfície per interactuar amb la base de dades
//Per definir

import jaumesitos.backend.demo.infrastructure.db.dbo.LligaDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataLLigaRepository extends JpaRepository<LligaDBO, Integer> {
    LligaDBO getLeagueById(int id);
    boolean deleteLeagueById(int id);
}
