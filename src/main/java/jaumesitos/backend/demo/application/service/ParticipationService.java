package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IParticipationRepository;
import jaumesitos.backend.demo.domain.Participation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {
    private final IParticipationRepository participationRepository;


    public ParticipationService(IParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public List<Participation> getParticipations(Integer leagueId){return this.participationRepository.getParticipations(leagueId);}

    public void postParticipation(Participation c){
        this.participationRepository.postParticipation(c);
    }
}
