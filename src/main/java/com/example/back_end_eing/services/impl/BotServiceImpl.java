package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.BotResponseDTO;
import com.example.back_end_eing.dto.BotSummaryResponseDTO;
import com.example.back_end_eing.exceptions.BotNotFoundException;
import com.example.back_end_eing.exceptions.RepeatedBotException;
import com.example.back_end_eing.exceptions.UserNotFoundException;
import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.services.BotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end_eing.dto.BotDTO;
import lombok.AllArgsConstructor;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BotServiceImpl implements BotService {

    private BotRepository botRepository;

    private UsuarioRepository userRepository;

    public void BotRegistro(BotDTO botdto){
        //mirar si existe un bot con esa api
        if(botRepository.findByApiKey(botdto.getEndpoint()).isEmpty()){
            //crear objeto usuario segun el id
            Usuario user = getUser(botdto.getUserId());
            Bot bot = new Bot(botdto, user);
            //registrar bot a la base de datos
            botRepository.save(bot);
        }else{
            throw(new RepeatedBotException(botdto.getEndpoint()));
        }
    }

    private Usuario getUser(int id){
        return userRepository.findById((long) id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<BotSummaryResponseDTO> listarBots(Long userId) {
        Optional<List<Bot>> listaBots = null;
        try{
            listaBots = botRepository.findByUsuarioId(userId);
        }catch (Exception e){
            throw(new UserNotFoundException(0));
        }
        
        return listaBots
                .map(listaBot ->
                        listaBot
                                .stream()
                                .map(bot -> BotSummaryResponseDTO
                                        .builder()
                                        .name(bot.getNombreBot())
                                        .description(bot.getDescripcionBot())
                                        .id(bot.getId())
                                        .urlImage(bot.getFotoBot())
                                        .build()
                                ).toList()
                )
                .orElseGet(() -> Collections.emptyList());
    }

    /*
     * Posibles errores:
     * Bot no encontrado
     */
    @Override
    public BotResponseDTO obtenerBot(Long botId) {
        Optional<Bot> bot = null;
        try{
            bot = botRepository.findById(botId);
        }catch(Exception e){
            throw(new BotNotFoundException(botId));
        }
        return bot.map(detallesBot -> BotResponseDTO
                        .builder()
                        .urlImage(detallesBot.getFotoBot())
                        .name(detallesBot.getNombreBot())
                        .description(detallesBot.getDescripcionBot())
                        .nWins(detallesBot.getNumVictorias())
                        //.nDraws(detallesBot.getNumEmpates())
                        //.nLosses(detallesBot.getNumDerrotas())
                        .botId(detallesBot.getId())
                        .build()
                )
                .orElseGet(() -> BotResponseDTO.builder().build());
    }

}