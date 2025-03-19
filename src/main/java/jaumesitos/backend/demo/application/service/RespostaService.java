package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IRespostaRepository;
import jaumesitos.backend.demo.domain.Resposta;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RespostaService {
    private final IRespostaRepository respostaRepository;

    public RespostaService(IRespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    public void registerResposta(String argument, LocalDateTime date) {
        Resposta resposta = new Resposta(UUID.randomUUID().toString(), argument, date);
        respostaRepository.save(resposta);
    }

    public Optional<Resposta> getRespostaById(String id) {
        return respostaRepository.findById(id);
    }
}
