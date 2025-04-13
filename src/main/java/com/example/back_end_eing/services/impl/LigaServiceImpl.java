package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.LeagueResponseDTO;
import com.example.back_end_eing.exceptions.*;
import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.dto.ParticipationResponseDTO;

import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.services.LigaService;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end_eing.repositories.ClasificacionRepository;
import com.example.back_end_eing.repositories.LigaRepository;
import com.example.back_end_eing.models.Clasificacion;

import com.example.back_end_eing.constants.EstadoLigaConstants;
import com.example.back_end_eing.dto.LeagueDTO;
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
                            .urlImagen(liga.getImagen())
                            .rounds(liga.getNumJornadas())
                            .matchTime(null)
                            .bots(bots.stream().map((bot -> bot.getId().intValue())).toList())
                            .build()
            );
        }

        return leagueResponseDTOS;
    }


    @Override
    public void deleteLiga(Long id) {
        Liga league = ligaRepository.findById(id)
                .orElseThrow(() -> new LigaNotFoundException(id));

        ligaRepository.delete(league);
    }

    // TODO Añadir error por liga repetida -> por lo que parece no hay atributos unicos, por lo que no hay repetición de ligas??
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

    public void actualizarLiga(LeagueDTO ligaDto, Long id){
        Optional<Liga> consulta = null;
        consulta = ligaRepository.findById(id);
        if(!consulta.isPresent()){
            throw(new LigaNotFoundException(id));
        }
        Liga liga = consulta.get();

        Optional<Usuario> user = usuarioRepository.findById((long)ligaDto.getCreador());
        if(!user.isPresent()){
            throw(new UserNotFoundException(0));
        }

        //el nuevo numero de bots no puede ser menor a los bots inscritos actualmente a la liga, además, ha de ser par
        if (ligaDto.getBots().length % 2 != 0) {
            throw new IncorrectNumBotsException(ligaDto.getBots().length);
        }
        List<Clasificacion> bots = clasificacionRepository.findByLigaId(id);
        if(ligaDto.getBots().length < bots.size()){
            throw new IncorrectNumBotsException(ligaDto.getBots().length);
        }

        liga.setNombreLiga(ligaDto.getName());
        liga.setImagen(ligaDto.getUrlImagen());
        liga.setNumJornadas(ligaDto.getRounds());
        liga.setNumBots(ligaDto.getBots().length);
        liga.setUsuario(user.get());

        ligaRepository.save(liga);
    }


    @Override
    public void registerBotToLeague(Long botId, Long leagueId) {

        Bot bot = botRepository.findById(botId)
                .orElseThrow(() -> new BotNotFoundException());

        Liga league = ligaRepository.findById(leagueId)
                .orElseThrow(() -> new LigaNotFoundException(leagueId));


        if (clasificacionRepository.findByBotIdAndLigaId(botId, leagueId) != null) {
            throw new IllegalArgumentException("El bot ya está registrado en esta liga");
        }

        Clasificacion clasificacion = new Clasificacion(bot, league);

        clasificacionRepository.save(clasificacion);
    }


    private Usuario getUsuario(int id){
        return usuarioRepository.findById((long) id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}

