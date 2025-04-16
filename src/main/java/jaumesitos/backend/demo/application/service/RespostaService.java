package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IRespostaRepository;
import jaumesitos.backend.demo.domain.Resposta;
import jaumesitos.backend.demo.infrastructure.db.dbo.RespostaDBO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RespostaService {
    private final IRespostaRepository respostaRepository;

    public RespostaService(IRespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    public void registerResposta(String argument, LocalDateTime date) {
        Resposta resposta = new Resposta(UUID.randomUUID().toString(), argument, date);
        respostaRepository.save(resposta);
    }

    public List<Resposta> getMesgesByMatchID(int matchId) {
        return respostaRepository.findByMatchId(matchId);
    }
}
