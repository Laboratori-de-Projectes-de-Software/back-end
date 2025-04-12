package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Participation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClassificacioRepository {
    void postClassificacio(Participation c);
    List<Participation> getClassifications(Integer leagueId);
}
