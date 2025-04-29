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

    public BotResponseDTO BotRegistro(BotDTO botdto) {
        BotResponseDTO botResponseDTO = new BotResponseDTO();
        //mirar si existe un bot con esa api y nombre
        if (botRepository.findByApiKey(botdto.getEndpoint()).isEmpty()) {
            if (botRepository.findByNombreBot(botdto.getName()).isEmpty()) {
                //crear objeto usuario segun el id
                Usuario user = getUser(botdto.getUserId());
                Bot bot = new Bot(botdto, user);
                //registrar bot a la base de datos
                Bot saved = botRepository.save(bot);
                botResponseDTO = new BotResponseDTO(botdto, saved.getId(), saved.getFotoBot());
            }
        } else {
            throw (new RepeatedBotException(botdto.getEndpoint()));
        }
        return botResponseDTO;
    }

    private Usuario getUser(int id) {
        return userRepository.findById((long) id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<BotSummaryResponseDTO> listarBots(Long userId) {
        Optional<List<Bot>> listaBots = null;
        try {
            listaBots = Optional.ofNullable(botRepository.findByUsuarioId(userId));
        } catch (Exception e) {
            throw (new UserNotFoundException(0));
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
        if (bot.isEmpty()) {
            throw (new BotNotFoundException());
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

    @Override
    public List<BotSummaryResponseDTO> getAllBots() {
        Optional<List<Bot>> listaBots = null;
        try {
            listaBots = Optional.of(botRepository.findAll());
        } catch (Exception e) {
            throw (new UserNotFoundException(0));
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
    public Bot getBotById(Long id) {
        Bot bot = botRepository.findById(id)
                .orElseThrow();

        return bot;
    }

    public BotResponseDTO actualizarBot(BotDTO botDTO, Long id){
        Optional<Bot> consulta = null;
        consulta = botRepository.findById(id);
        if(!consulta.isPresent()){
            throw(new BotNotFoundException());
        }
        Bot bot = consulta.get();


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

        Bot res = botRepository.save(bot);
        return new BotResponseDTO(botDTO, id, res.getFotoBot());
    }

    @Override
    public void deleteBot(Long id) {
        Bot bot = botRepository.findById(id)
                .orElseThrow(() -> new BotNotFoundException());

        botRepository.delete(bot);
    }

}