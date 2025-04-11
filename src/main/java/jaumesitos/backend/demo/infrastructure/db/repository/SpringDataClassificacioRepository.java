package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.infrastructure.db.dbo.BotDBO;
import jaumesitos.backend.demo.infrastructure.db.dbo.ClassificacioDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataClassificacioRepository extends JpaRepository<ClassificacioDBO, Integer> {
}
