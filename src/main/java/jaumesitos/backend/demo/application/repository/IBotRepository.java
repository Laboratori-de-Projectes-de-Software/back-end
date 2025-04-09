package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Bot;
import java.util.List;
import java.util.Optional;

public interface IBotRepository {
    Bot save(Bot bot);
    Optional<Bot> findById(int id);
    List<Bot> findAll();
    List<Bot> findByOwnerId(int ownerId);
}
