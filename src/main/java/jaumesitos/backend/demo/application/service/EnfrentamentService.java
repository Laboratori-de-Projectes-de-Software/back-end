package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IEnfrentamentRepository;
import jaumesitos.backend.demo.domain.Enfrentament;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class EnfrentamentService {
    private final IEnfrentamentRepository enfrentamentRepository;

    public EnfrentamentService(IEnfrentamentRepository enfrentamentRepository) {
        this.enfrentamentRepository = enfrentamentRepository;
    }

    public void registerEnfrentament(String idBotLocal, String idBotVisitant, LocalDateTime date, String resultat) {
        Enfrentament enfrentament = new Enfrentament(UUID.randomUUID().toString(), idBotLocal, idBotVisitant, date, resultat);
        enfrentamentRepository.save(enfrentament);
    }

    public Optional<Enfrentament> getEnfrentamentById(String id) {
        return enfrentamentRepository.findById(id);
    }

    public List<Enfrentament> getAllEnfrentaments() {
        return enfrentamentRepository.findAll();
    }
}