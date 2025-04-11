package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IClassificacioRepository;
import jaumesitos.backend.demo.config.DuplicateEntityException;
import jaumesitos.backend.demo.domain.Classificacio;
import jaumesitos.backend.demo.infrastructure.db.dbo.ClassificacioDBO;
import jaumesitos.backend.demo.infrastructure.db.mapper.ClassificacioDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassificacioDBORepository implements IClassificacioRepository {
    private final ClassificacioDBOMapper mapper;
    private final SpringDataClassificacioRepository repository;
    @Override
    public void postClassificacio(Classificacio c) {
        ClassificacioDBO dbo = mapper.toDBO(c);
        try {
            repository.save(dbo);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntityException("Classificació ja existent", e);
        }
    }
}
