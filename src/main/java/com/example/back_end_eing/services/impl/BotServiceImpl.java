package com.example.back_end_eing.services.impl;

import com.example.back_end_eing.services.BotService;

import org.springframework.stereotype.Service;

import com.example.back_end_eing.repositories.BotRepository;
import com.example.back_end_eing.repositories.UsuarioRepository;
import com.example.back_end_eing.models.Bot;
import com.example.back_end_eing.models.Usuario;
import com.example.back_end_eing.exceptions.RepeatedBotException;
import com.example.back_end_eing.exceptions.UserNotFoundException;;

@Service
public class BotServiceImpl implements BotService{
    private BotRepository botRepository;
    private UsuarioRepository userRepository;
    private Bot bot;
    private Usuario user;

    public void BotRegistro(String nombre, String descripcion, String foto, Integer victorias, Integer numJornadas, String API, Long id){
        //mirar si existe un bot con esa api        
        if(botRepository.findByApiKey(API).isEmpty()){
            //crear objeto usuario segun el id
            user = getUser(id);
            bot = new Bot(nombre, descripcion, foto, victorias, numJornadas, API, user);
            //registrar bot a la base de datos
            botRepository.save(bot);
        }else{
            throw(new RepeatedBotException(API));
        }
    }

    private Usuario getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
