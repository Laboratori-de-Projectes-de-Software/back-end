package jaumesitos.backend.demo.infrastructure.db.repository;

import jaumesitos.backend.demo.application.repository.IEnfrentamentRepository;
import jaumesitos.backend.demo.domain.Enfrentament;
import jaumesitos.backend.demo.infrastructure.db.mapper.EnfrentamentDBOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EnfrentamentDBORepository implements IEnfrentamentRepository {
    //private final SpringDataEnfrentamentRepository springDataRepository;
    private final EnfrentamentDBOMapper mapper;

    @Override
    public void save(Enfrentament enfrentament) {
        //springDataRepository.save(mapper.toDBO(enfrentament));
    }

    @Override
    public Optional<Enfrentament> findById(String id) {
        //return springDataRepository.findById(id).map(mapper::toDomain);
        return null;
    }

    @Override
    public List<Enfrentament> findAll() {
        /*return springDataRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());*/
        return null;
    }
}