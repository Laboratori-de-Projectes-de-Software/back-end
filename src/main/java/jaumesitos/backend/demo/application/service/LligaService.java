package jaumesitos.backend.demo.application.service;

import jaumesitos.backend.demo.application.repository.ILligaRepository;
import jaumesitos.backend.demo.domain.League;
import jaumesitos.backend.demo.infrastructure.res.dto.LeagueResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LligaService {
    private final ILligaRepository illigarepository;

    public LligaService(ILligaRepository illigarepository) {
        this.illigarepository = illigarepository;
    }

    public void postLliga(League lliga){
        illigarepository.postLliga(lliga);
    }

    public List<League> getLeagues(Integer owner){
        return illigarepository.getLeagues(owner);
    }

    public League getLeagueById(int id) {
        return illigarepository.findById(id).orElseThrow(() -> new RuntimeException("Liga no encontrada"));
    }

    public void deleteLeagueById(int id) {
        if (!illigarepository.deleteById(id)) {
            throw new RuntimeException("No existe esta liga");
        }
    }

}
