package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.ILligaRepository;
import jaumesitos.backend.demo.domain.Lliga;

public class LligaService {
    private final ILligaRepository illigarepository;

    public LligaService(ILligaRepository illigarepository) {
        this.illigarepository = illigarepository;
    }

    public Lliga postLliga(Lliga lliga){
        return illigarepository.postLliga(lliga);
    }
}
