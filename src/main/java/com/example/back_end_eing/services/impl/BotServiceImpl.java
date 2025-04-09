package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.services.BotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.dto.BotDTO;
import com.example.back_end_eing.exceptions.RepeatedBotException;
import com.example.back_end_eing.exceptions.UserNotFoundException;;

@Service
public class BotServiceImpl implements BotService{
    @Autowired
    private BotRepository botRepository;
    @Autowired
    private UsuarioRepository userRepository;
    private Bot bot;
    private Usuario user;

    public void BotRegistro(BotDTO botdto){
        //mirar si existe un bot con esa api        
        if(botRepository.findByApiKey(botdto.getEndpoint()).isEmpty()){
            //crear objeto usuario segun el id
            user = getUser(botdto.getUserId());
            bot = new Bot(botdto, user);
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

}
