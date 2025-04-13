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
        //mirar si existe un bot con esa api y nombre
        if(botRepository.findByApiKey(botdto.getEndpoint()).isEmpty()){
            if(botRepository.findByNombreBot(botdto.getName()).isEmpty()){
                //crear objeto usuario segun el id
                Usuario user = getUser(botdto.getUserId());
                Bot bot = new Bot(botdto, user);
                //registrar bot a la base de datos
                botRepository.save(bot);
            }
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
        listaBots = botRepository.findByUsuarioId(userId);
        if (listaBots.isPresent()){
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

    @Override
    public BotResponseDTO obtenerBot(Long botId) {
        Optional<Bot> bot = null;
        bot = botRepository.findById(botId);
        if(bot.isPresent()){
            throw(new BotNotFoundException());
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

    public void actualizarBot(BotDTO botDTO, Long id){
        Optional<Bot> consulta = null;
        consulta = botRepository.findById(id);
        if(!consulta.isPresent()){
            throw(new BotNotFoundException());
        }
        Bot bot = consulta.get();

        //controlamos que no repita el nombre y el apikey
        consulta = botRepository.findByNombreBot(botDTO.getName());
        if(consulta.isPresent()){
            if(consulta.get().getId() == bot.getId()){
                throw(new RepeatedBotException(null));
            }
        }

        consulta = botRepository.findByApiKey(botDTO.getEndpoint());
        if(consulta.isPresent()){
            if(consulta.get().getId() == bot.getId()){
                throw(new RepeatedBotException(null));
            }
        }

        Optional<Usuario> user = userRepository.findById((long)botDTO.getUserId());
        if(!user.isPresent()){
            throw(new UserNotFoundException(0));
        }
        //actualizamos los datos
        bot.setUsuario(user.get());
        bot.setNombreBot(botDTO.getName());
        bot.setDescripcionBot(botDTO.getDescription());
        bot.setFotoBot(botDTO.getUrlImagen());
        bot.setApiKey(botDTO.getEndpoint());

        botRepository.save(bot);
    }

}