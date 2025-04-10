package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.ILligaRepository;
import jaumesitos.backend.demo.domain.Lliga;
import org.springframework.stereotype.Service;

@Service
public class LligaService {
    private final ILligaRepository illigarepository;

    public LligaService(ILligaRepository illigarepository) {
        this.illigarepository = illigarepository;
    }

    public Lliga postLliga(Lliga lliga){
        return illigarepository.postLliga(lliga);
    }

    public Lliga getLeagueById(int id) {
        return illigarepository.findById(id).orElseThrow(() -> new RuntimeException("Liga no encontrada"));
    }

    public void deleteLeagueById(int id) {
        if (!illigarepository.deleteById(id)) {
            throw new RuntimeException("No existe esta liga");
        }
    }
}
