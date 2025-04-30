package org.example.backend.repository;

import org.example.backend.databaseapi.application.port.out.mensaje.CreateMensajePort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajeBotPort;
import org.example.backend.databaseapi.application.port.out.mensaje.FindMensajePartidaPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.bot.BotId;
import org.example.backend.databaseapi.domain.mensaje.Mensaje;
import org.example.backend.databaseapi.domain.partida.PartidaId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Order(5)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MensajeAdapterIntegrationTest {

    @Autowired
    private CreateMensajePort createMensajePort;
    @Autowired
    private FindMensajePartidaPort findMensajePartidaPort;
    @Autowired
    private FindMensajeBotPort findMensajeBotPort;


    private static Mensaje created;

    @Test
    @Order(1)
    public void createMensajeTest(){
        Mensaje mensaje= Mensaje.builder()
                .texto("TESTMENSAJE")
                .partida(new PartidaId(1))
                .bot(new BotId(1))
                .build();

        created=createMensajePort.createMensaje(mensaje);

        Assertions.assertNotEquals(created,null);
        Assertions.assertTrue(created.getMensajeId().value()>0);
    }

    @Test
    @Order(2)
    public void findMensajesPartidaTest(){
        List<Mensaje> found=findMensajePartidaPort.findMensajePartida(created.getPartida().value());

        Assertions.assertNotEquals(found,null);
        Assertions.assertFalse(found.isEmpty());
    }

    @Test
    @Order(3)
    public void findMensajesBotTest(){
        List<Mensaje> found=findMensajeBotPort.findMensajeBot(created.getBot().value());

        Assertions.assertNotEquals(found,null);
        Assertions.assertFalse(found.isEmpty());
    }
}
