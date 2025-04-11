package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.IClassificacioRepository;
import jaumesitos.backend.demo.domain.Classificacio;
import org.springframework.stereotype.Service;

@Service
public class ClassificacioService  {
    private final IClassificacioRepository classificacioRepository;


    public ClassificacioService(IClassificacioRepository classificacioRepository) {
        this.classificacioRepository = classificacioRepository;
    }

    public void postClassificacio(Classificacio c){
        this.classificacioRepository.postClassificacio(c);
    }
}
