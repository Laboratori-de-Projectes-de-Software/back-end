package org.example.backend.repository;

import org.example.backend.databaseapi.application.port.out.bot.*;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Order(2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BotAdapterIntegrationTest {

    @Autowired
    private CreateBotPort createBotPort;
    @Autowired
    private FindBotPort findBotPort;
    @Autowired
    private UpdateBotPort updateBotPort;
    @Autowired
    private DeleteBotPort deleteBotPort;
    @Autowired
    private FindAllUserBots findAllUserBots;
    @Autowired
    private FindAllBotsPort findAllBotsPort;

    private static Bot created;

    @Test
    @Order(1)
    public void createBotTest(){
        Bot bot=Bot.builder()
                .nombre("TestBot")
                .cualidad("TestQuality")
                .prompt("Eres un bot beep boop")
                .usuario(new UsuarioId(1))
                .url("urlawaaaaa")
                .build();

        created=createBotPort.createBot(bot)
                .orElseThrow();

        Assertions.assertNotEquals(created,null);
        Assertions.assertTrue(created.getIdBot().value() > 0);
    }

    @Test
    @Order(2)
    public void findBotTest(){
        Bot foundBot=findBotPort.findBot(created.getIdBot().value())
                        .orElseThrow();

        Assertions.assertNotEquals(foundBot,null);
        Assertions.assertEquals(created.getNombre(),foundBot.getNombre());
        Assertions.assertEquals(created.getPrompt(),foundBot.getPrompt());
        Assertions.assertEquals(created.getUsuario().value(),foundBot.getUsuario().value());
    }

    @Test
    @Order(3)
    public void updateBotTest(){
        Bot bot=Bot.builder()
                .prompt("Eres un bot beep boop pero 2 veces mas")
                .url("urlawaaaaa2")
                .build();

        created=updateBotPort.updateBot(bot,created.getIdBot().value());

        Assertions.assertNotEquals(created,null);
        Assertions.assertNotEquals(created.getNombre(),null);
        Assertions.assertEquals(created.getPrompt(),bot.getPrompt());
        Assertions.assertEquals(created.getUrl(),bot.getUrl());
        Assertions.assertEquals(created.getUsuario().value(),1);
    }


}
