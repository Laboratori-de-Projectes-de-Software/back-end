package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.dto.ParticipationResponseDTO;

import com.example.back_end_eing.services.LigaService;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.repositories.ClasificacionRepository;
import com.example.back_end_eing.repositories.LigaRepository;
import com.example.back_end_eing.models.Clasificacion;
import com.example.back_end_eing.exceptions.LigaNotFoundException;
import com.example.back_end_eing.constants.EstadoLigaConstants;
import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.exceptions.IncorrectNumBotsException;
import com.example.back_end_eing.exceptions.UserNotFoundException;
import com.example.back_end_eing.models.Liga;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.repositories.UsuarioRepository;

@AllArgsConstructor
@Service
public class LigaServiceImpl implements LigaService{

    @Autowired
    private BotRepository botRepository;
    @Autowired
    private LigaRepository ligaRepository;

    @Autowired
    private ClasificacionRepository clasificacionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;



    @Override
    public List<ParticipationResponseDTO> getClasificacion(Long id) {

        ligaRepository.findById(id)
                    .orElseThrow(() -> new LigaNotFoundException(id));

        List<Clasificacion> clasificaciones = clasificacionRepository.findByLigaId(id) != null
                ? clasificacionRepository.findByLigaId(id)
                : new ArrayList<>();

        clasificaciones.sort(Comparator.comparing(Clasificacion::getPuntuacionBot).reversed());

        //return ParticipationResponseDTO list
        return clasificaciones.stream()
                .map(cl -> {
                    ParticipationResponseDTO dto = new ParticipationResponseDTO();
                    dto.setBotId(cl.getBot().getId().intValue());
                    dto.setName(cl.getBot().getNombreBot());
                    dto.setPoints(cl.getPuntuacionBot());
                    dto.setPosition(clasificaciones.indexOf(cl)+1);
                    return dto;
                })
                .toList();

    }

    public LeagueResponseDTO getLiga(Long id) {

        Liga league = ligaRepository.findById(id)
                .orElseThrow(() -> new LigaNotFoundException(id));

        List<Integer> bots = clasificacionRepository.findBotIdsByLigaId(id);

        return new LeagueResponseDTO(league, bots);

    }

    public List<LeagueResponseDTO> obtenerLigas() {
        List<Liga> ligas = ligaRepository.findAll();

        List<LeagueResponseDTO> leagueResponseDTOS = new ArrayList<>();
        for(Liga liga : ligas){

            List<Bot> bots = botRepository.findByLeague(liga.getId());

            leagueResponseDTOS.add(
                    LeagueResponseDTO.builder()
                            .leagueId(liga.getId().intValue())
                            .status(liga.getEstado())
                            .name(liga.getNombreLiga())
                            .user(liga.getUsuario().getNombreUsuario())
                            .urlImagen(null)
                            .rounds(liga.getNumJornadas())
                            .matchTime(null)
                            .bots(bots.stream().map((bot -> bot.getId().intValue())).toList())
                            .build()
            );
        }

        return leagueResponseDTOS;
    }

    public List<Liga> obtenerLigasUser(Long id) {
        return StreamSupport
                .stream(ligaRepository.findAll().spliterator(), false)
                .filter(liga -> liga.getUsuario().getId().equals(id))
                .collect(Collectors.toList());
    }



    @Override
    public void deleteLiga(Long id) {
        Liga league = ligaRepository.findById(id)
                .orElseThrow(() -> new LigaNotFoundException(id));

        ligaRepository.delete(league);
    }

    public void LigaRegistro(LeagueDTO ligaDto){
        // solo un número de bots par, controlar en el front-end números > 0
        if (ligaDto.getBots().length % 2 != 0) {
            throw new IncorrectNumBotsException(ligaDto.getBots().length);
        }
        Usuario usuario = getUsuario(ligaDto.getCreador());
        // establecer estado = ABIERTA por defecto si el usuario no lo especifica

        String estado = EstadoLigaConstants.ABIERTA;
        Liga liga = new Liga(ligaDto.getName(), ligaDto.getRounds(), ligaDto.getBots().length, estado, ligaDto.getUrlImagen(), 1, usuario);
        ligaRepository.save(liga);
    }


    private Usuario getUsuario(int id){
        return usuarioRepository.findById((long) id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}

