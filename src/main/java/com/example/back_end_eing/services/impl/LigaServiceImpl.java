package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.services.LigaService;

import jakarta.transaction.Transactional;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.repositories.ClasificacionRepository;
import com.example.back_end_eing.repositories.LigaRepository;
import com.example.back_end_eing.models.Clasificacion;
import com.example.back_end_eing.exceptions.BotNotFoundException;
import com.example.back_end_eing.exceptions.ClasificacionLigaNotFoundException;
import com.example.back_end_eing.exceptions.ClasificacionNotFoundException;
import com.example.back_end_eing.exceptions.LigaNotFoundException;
import com.example.back_end_eing.constants.EstadoLigaConstants;
import com.example.back_end_eing.dto.LeagueDTO;
import com.example.back_end_eing.exceptions.IncorrectNumBotsException;
import com.example.back_end_eing.exceptions.UserNotFoundException;
import com.example.back_end_eing.models.Liga;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.repositories.UsuarioRepository;

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
    private Liga liga;
    private Usuario usuario;

    @Override
    public void LigaActualización(Long liga, Long local, Long visitante, String resultado) {      
        comprobaciones(local, visitante, liga);

        //actualizaciones de puntos
        Clasificacion clasificacion = clasificacionRepository.findByBotIdAndLigaId(local, liga);
        actualizarPuntos(clasificacion, resultado, true);
        clasificacion = clasificacionRepository.findByBotIdAndLigaId(visitante, liga);
        actualizarPuntos(clasificacion, resultado, false);
        
        actualizarPosiciones(liga);
    }

    @Override
    public List<Clasificacion> LigaClasificacion(Long liga) {
        ligaExiste(liga);
        clasificacionExisteLiga(liga);

        List<Clasificacion> clasificaciones = clasificacionRepository.findByLigaId(liga);
        clasificaciones.sort(Comparator.comparing(Clasificacion::getPuntuacionBot).reversed());
        return clasificaciones;
    }

    private void comprobaciones(Long local, Long visitante, Long liga){
        botExiste(local);
        botExiste(visitante);
        ligaExiste(liga);
        clasificacionExiste(local, liga);
        clasificacionExiste(visitante, liga);
    }

    private void botExiste(Long bot){
        if(botRepository.findById(bot) == null){
            throw(new BotNotFoundException(bot));
        }
    }

    private void ligaExiste(Long liga){
        if(ligaRepository.findById(liga) == null){
            throw(new LigaNotFoundException(liga));
        }
    }

    private void clasificacionExiste(Long bot, long liga){
        if(clasificacionRepository.findByBotIdAndLigaId(bot, liga) == null){
            throw new ClasificacionNotFoundException(bot, liga);
        }
    }

    private void clasificacionExisteLiga(long liga){
        if(clasificacionRepository.findByLigaId(liga) == null){
            throw new ClasificacionLigaNotFoundException(liga);
        }
    }

    private void actualizarPuntos(Clasificacion clasificacion, String resultado, boolean local){
        switch (resultado) {
            case "local":
                if(local){
                    clasificacion.setVictorias(clasificacion.getVictorias()+1);
                    clasificacion.setPuntuacionBot(clasificacion.getPuntuacionBot()+3);
                }else{
                    clasificacion.setVictorias(clasificacion.getDerrotas()+1);
                }
                break;

            case "visitante":
                if(local){
                    clasificacion.setVictorias(clasificacion.getDerrotas()+1);
                }else{
                    clasificacion.setVictorias(clasificacion.getVictorias()+1);
                    clasificacion.setPuntuacionBot(clasificacion.getPuntuacionBot()+3);
                }
                break;

            case "empate":
                clasificacion.setEmpates(clasificacion.getEmpates()+1);
                clasificacion.setPuntuacionBot(clasificacion.getPuntuacionBot()+1);
                break;
        
            default:
                break;
        }
        clasificacionRepository.save(clasificacion);
    }

    @Transactional
    private void actualizarPosiciones(Long liga){
        List<Clasificacion> clasificaciones = LigaClasificacion(liga);
        int pos = 1;
        for (Clasificacion clasificacion : clasificaciones) {
            clasificacion.setPosicion(pos);
            pos++;
        }
    }

    

    public void LigaRegistro(LeagueDTO ligaDto){
        // solo un número de bots par, controlar en el front-end números > 0
        if (ligaDto.getBots().length % 2 != 0) {
            throw new IncorrectNumBotsException(ligaDto.getBots().length);
        }
        usuario = getUsuario(ligaDto.getCreador());
        // establecer estado = ABIERTA por defecto si el usuario no lo especifica
        String estado = null;
        if (estado == null || estado.isEmpty()) {
            estado = EstadoLigaConstants.ABIERTA;
        }
        liga = new Liga(ligaDto.getName(), ligaDto.getRounds(), ligaDto.getBots().length, estado, ligaDto.getUrlImagen(), 1, usuario);
        ligaRepository.save(liga);
    }

    private Usuario getUsuario(int id){
        return usuarioRepository.findById((long) id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
