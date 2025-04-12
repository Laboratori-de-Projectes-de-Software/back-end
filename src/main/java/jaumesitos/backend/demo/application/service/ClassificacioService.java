package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IClassificacioRepository;
import jaumesitos.backend.demo.domain.Participation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificacioService  {
    private final IClassificacioRepository classificacioRepository;


    public ClassificacioService(IClassificacioRepository classificacioRepository) {
        this.classificacioRepository = classificacioRepository;
    }

    public List<Participation> getClassifications(Integer leagueId){return this.classificacioRepository.getClassifications(leagueId);}

    public void postClassificacio(Participation c){
        this.classificacioRepository.postClassificacio(c);
    }
}
