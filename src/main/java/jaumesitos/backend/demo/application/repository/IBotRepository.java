package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Bot;
import java.util.List;
import java.util.Optional;

public interface IBotRepository {
    void save(Bot bot);
    Optional<Bot> findById(String id);
    List<Bot> findAll();
}
