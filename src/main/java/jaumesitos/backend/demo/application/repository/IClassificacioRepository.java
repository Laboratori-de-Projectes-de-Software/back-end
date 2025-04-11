package jaumesitos.backend.demo.application.repository;

import jaumesitos.backend.demo.domain.Classificacio;
import org.springframework.http.ResponseEntity;

public interface IClassificacioRepository {
    void postClassificacio(Classificacio c);
}
