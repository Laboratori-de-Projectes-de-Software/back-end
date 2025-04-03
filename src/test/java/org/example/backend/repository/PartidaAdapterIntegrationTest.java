package org.example.backend.repository;

import org.example.backend.databaseapi.application.port.out.bot.*;
import org.example.backend.databaseapi.application.port.out.partida.CreatePartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.FindLigaPartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.FindPartidaPort;
import org.example.backend.databaseapi.application.port.out.partida.UpdatePartidaPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.liga.LigaId;
import org.example.backend.databaseapi.domain.partida.Estado;
import org.example.backend.databaseapi.domain.partida.Partida;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Order(4)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartidaAdapterIntegrationTest {

    @Autowired
    private CreatePartidaPort createPartidaPort;
    @Autowired
    private FindPartidaPort findPartidaPort;
    @Autowired
    private UpdatePartidaPort updatePartidaPort;
    @Autowired
    private FindLigaPartidaPort findLigaPartidaPort;

    private static Partida created;

    @Test
    @Order(1)
    public void createPartidaTest(){
        Partida partida= Partida.builder()
                .liga(new LigaId(1))
                .estado(Estado.EnProgreso)
                .duracionTotal(3456)
                .build();

        created=createPartidaPort.createPartida(partida);

        Assertions.assertNotEquals(created,null);
        Assertions.assertTrue(created.getPartidaId().value()>0);
    }

    @Test
    @Order(2)
    public void findPartidaTest(){
        Partida found=findPartidaPort.findParida(created.getPartidaId().value())
                .orElseThrow();

        Assertions.assertNotEquals(found,null);
        Assertions.assertEquals(created.getLiga().value(),found.getLiga().value());
        Assertions.assertEquals(created.getEstado(),found.getEstado());
    }

    @Test
    @Order(3)
    public void findLigaTest(){
        List<Partida> found=findLigaPartidaPort.findLigaPartida(created.getLiga().value());

        Assertions.assertNotEquals(found,null);
        Assertions.assertFalse(found.isEmpty());
    }

}
