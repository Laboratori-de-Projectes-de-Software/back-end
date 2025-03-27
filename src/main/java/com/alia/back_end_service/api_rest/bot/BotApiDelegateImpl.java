package com.alia.back_end_service.api_rest.bot;

import com.alia.back_end_service.api.BotsApiDelegate;
import com.alia.back_end_service.api_model.BotRegister;
import com.alia.back_end_service.api_model.BotsRegisterPost201Response;
import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotRegistrationPortAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class BotApiDelegateImpl implements BotsApiDelegate {
    private final BotRegistrationPortAPI botRegistrationPortAPI;

    @Override
    public ResponseEntity<BotsRegisterPost201Response> botsRegisterPost(BotRegister botRegister) {
        System.out.println("Hola Botardo");
        // Se extrae el identificador del usuario autenticado del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (authentication != null) ? authentication.getName() : "usuarioEjemplo"; // Fallback para pruebas
        System.out.println("Hola Botardo");

        // Mapeo al objeto de dominio
        Bot bot = new Bot(
                botRegister.getName(),
                botRegister.getDescription(),
                botRegister.getEndpoint(),
                UUID.randomUUID().toString(),    // Se genera un token (aquí podrías invocar un servicio especializado)
                userId,
                Collections.emptyList()          // Inicialmente, sin ligas asociadas
        );

        botRegistrationPortAPI.registerBot(bot);
        System.out.println("Hola Botardo");
        BotsRegisterPost201Response response = new BotsRegisterPost201Response();
        response.setId(UUID.fromString(bot.getName()));
        response.setMessage("Bot registered successfully");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
