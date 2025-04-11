package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.dto.BotResponseDTO;
import com.example.back_end_eing.dto.BotSummaryResponseDTO;
import com.example.back_end_eing.exceptions.RepeatedBotException;
import com.example.back_end_eing.exceptions.UserNotFoundException;
import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.services.BotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BotServiceImpl implements BotService {

    private BotRepository botRepository;

    private UsuarioRepository userRepository;


    public void BotRegistro(String nombre, String descripcion, String foto, Integer victorias, Integer numJornadas, String API, Long id) {
        //mirar si existe un bot con esa api        
        if (botRepository.findByApiKey(API).isEmpty()) {
            //crear objeto usuario segun el id
            Usuario user = getUser(id);
            Bot bot = new Bot(nombre, descripcion, foto, victorias, numJornadas, API, user);
            //registrar bot a la base de datos
            botRepository.save(bot);
        } else {
            throw (new RepeatedBotException(API));
        }
    }

    private Usuario getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<BotSummaryResponseDTO> listarBots(Long userId) {
        Optional<List<Bot>> listaBots = botRepository.findByUsuarioId(userId);
        return listaBots
                .map(listaBot ->
                        listaBot
                                .stream()
                                .map(bot -> BotSummaryResponseDTO
                                        .builder()
                                        .name(bot.getNombreBot())
                                        .description(bot.getDescripcionBot())
                                        .id(bot.getId())
                                        .build()
                                ).toList()
                )
                .orElseGet(() -> Collections.emptyList());

    }

    @Override
    public BotResponseDTO obtenerBot(Long botId) {
        Optional<Bot> bot = botRepository.findById(botId);
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
