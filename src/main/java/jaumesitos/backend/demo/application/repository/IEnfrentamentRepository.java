package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Enfrentament;
import java.util.List;
import java.util.Optional;

public interface IEnfrentamentRepository {
    void save(Enfrentament enfrentament);
    Optional<Enfrentament> findById(String id);
    List<Enfrentament> findAll();
}
