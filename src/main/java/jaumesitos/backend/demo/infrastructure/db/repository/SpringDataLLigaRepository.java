package jaumesitos.backend.demo.infrastructure.db.repository;

//Interfície per interactuar amb la base de dades
//Per definir

import jaumesitos.backend.demo.infrastructure.db.dbo.LligaDBO;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataLLigaRepository {
    LligaDBO getLeagueById(int id);
    boolean deleteLeagueById(int id);
}
