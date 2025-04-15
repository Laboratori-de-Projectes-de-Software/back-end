package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.EnfrentamientoDTO;
import com.example.back_end_eing.dto.MatchResponseDTO;
import com.example.back_end_eing.models.*;
import com.example.back_end_eing.exceptions.*;
import com.example.back_end_eing.repositories.*;
import com.example.back_end_eing.services.EnfrentamientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EnfrentamientoServiceImpl implements EnfrentamientoService {

    private LigaRepository ligaRepository;
    private BotRepository botRepository;
    private JornadaRepository jornadaRepository;
    private EnfrentamientoRepository enfrentamientoRepository;
    private ParticipacionRepository participacionRepository;
    private ClasificacionRepository clasificacionRepository;

    // Constructor injection for all repositories
    public EnfrentamientoServiceImpl(LigaRepository ligaRepository,
                                     BotRepository botRepository,
                                     JornadaRepository jornadaRepository,
                                     EnfrentamientoRepository enfrentamientoRepository,
                                     ParticipacionRepository participacionRepository,
                                     ClasificacionRepository clasificacionRepository) {
        this.ligaRepository = ligaRepository;
        this.botRepository = botRepository;
        this.jornadaRepository = jornadaRepository;
        this.enfrentamientoRepository = enfrentamientoRepository;
        this.participacionRepository = participacionRepository;
        this.clasificacionRepository = clasificacionRepository;
    }

    @Override
    public Enfrentamiento obtenerEnfrentamiento(Long id) {
        return enfrentamientoRepository.findById(id)
                .orElseThrow(() -> new EnfrentamientoNotFoundException(id));
    }

    @Override
    public Enfrentamiento obtenerEnfrentamientoConParticipaciones(Long id) {
    // Fetch enfrentamiento with eager loading of participaciones
    Enfrentamiento enfrentamiento = enfrentamientoRepository.findById(id)
            .orElseThrow(() -> new EnfrentamientoNotFoundException(id));
    
    // Explicitly load participaciones if they're lazily loaded
    // This will force loading the participaciones and bots
    enfrentamiento.getParticipaciones().size();
    
    return enfrentamiento;
}

    public EnfrentamientoDTO obtenerConversacion(Long id) {
        Enfrentamiento enfrentamiento = obtenerEnfrentamiento(id);
        return new EnfrentamientoDTO(enfrentamiento.getClasificacion());
    }

    @Transactional
    public void generarEnfrentamientos(Long id) {
        Liga liga = ligaRepository.findById(id)
                .orElseThrow(() -> new LigaNotFoundException(id));

        List<Clasificacion> clasificaciones = clasificacionRepository.findByLigaId(id);
        List<Bot> bots = clasificaciones.stream().map(Clasificacion::getBot).toList();

        // se debe comprobar que haya no menos de 2 bots  ¿?

        int numeroBots = bots.size();
        int numJornadas = liga.getNumJornadas();

        List<Bot> RRbots = new ArrayList<>(bots);
        List<Bot> listaRotativa = new ArrayList<>(RRbots.subList(1, numeroBots));
        Bot primerBot = RRbots.getFirst();

        // generar jornadas y enfrentamientos
        for (int i = 0; i < numJornadas; i++) {
            Jornada jornada = new Jornada();
            jornada.setLiga(liga);
            jornada.setNumJornada(i+1);
            jornada.setNumBots(numeroBots);
            jornada.setEnfrentamientos(new HashSet<>());
            Jornada jornadaGuardada = jornadaRepository.save(jornada);

            Set<Enfrentamiento> enfrentamientos = new HashSet<>();

            if (!listaRotativa.isEmpty()) {
                Bot oponenteFijo = listaRotativa.getFirst();
                Enfrentamiento enfrentamiento = crearEnfrentamiento(jornadaGuardada, primerBot, oponenteFijo);
                enfrentamientos.add(enfrentamiento);
            }

            int mitad = numeroBots / 2;
            for (int k = 1; k < mitad; k++) {
                Bot bot1 = listaRotativa.get(k);
                Bot bot2 = listaRotativa.get(listaRotativa.size() - k);

                if (bot1 != null && bot2 != null) {
                    Enfrentamiento enfremamiento = crearEnfrentamiento(jornadaGuardada, bot1, bot2);
                    enfrentamientos.add(enfremamiento);
                }
            }

            jornadaGuardada.setEnfrentamientos(enfrentamientos);
            jornadaRepository.save(jornadaGuardada);
            liga.setEstado("EN CURSO");

            Collections.rotate(listaRotativa, 1);

        }


    }

    public List<MatchResponseDTO> getMatchesByLeagueId(Long leagueId) {
        Liga liga = ligaRepository.findById(leagueId)
                .orElseThrow(() -> new RuntimeException("Liga no encontrada"));

        List<MatchResponseDTO> matches = new ArrayList<>();

        //Ordenar por num de jornada
        List<Jornada> jornadasOrdenadas = liga.getJornadas().stream()
                .sorted(Comparator.comparing(Jornada::getNumJornada))
                .toList();

        for (Jornada jornada : jornadasOrdenadas) {
            for (Enfrentamiento enfrentamiento : jornada.getEnfrentamientos()) {
                List<String> fighters = enfrentamiento.getParticipaciones()
                        .stream()
                        .map(p -> p.getBot().getNombreBot())
                        .toList();

                matches.add(new MatchResponseDTO(
                        enfrentamiento.getId().intValue(),
                        enfrentamiento.getResultado()!=null ? "FINALIZADO" : "",
                        enfrentamiento.getResultado(),
                        fighters.toArray(new String[0]),
                        jornada.getNumJornada()
                ));
            }
        }

        return matches;
    }

    //PRIVATE METHODS

    private Enfrentamiento crearEnfrentamiento(Jornada jornada, Bot bot1, Bot bot2) {
        Enfrentamiento enfrentamiento = new Enfrentamiento();
        enfrentamiento.setJornada(jornada);
        enfrentamiento.setClasificacion(null);
        enfrentamiento.setResultado(null);
        enfrentamiento.setParticipaciones(new HashSet<>());
        Enfrentamiento enfrentamientoGuardado = enfrentamientoRepository.save(enfrentamiento);

        Participacion p1 = new Participacion();
        p1.setBot(bot1);
        p1.setEnfrentamiento(enfrentamientoGuardado);
        participacionRepository.save(p1);

        Participacion p2 = new Participacion();
        p2.setBot(bot2);
        p2.setEnfrentamiento(enfrentamientoGuardado);
        participacionRepository.save(p2);

        enfrentamientoGuardado.getParticipaciones().add(p1);
        enfrentamientoGuardado.getParticipaciones().add(p2);

        enfrentamientoRepository.save(enfrentamientoGuardado);

        return enfrentamientoGuardado;
    }

}
