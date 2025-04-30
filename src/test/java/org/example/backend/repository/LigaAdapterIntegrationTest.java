package org.example.backend.repository;

import org.example.backend.databaseapi.application.port.out.liga.CreateLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindAllLigasPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaPort;
import org.example.backend.databaseapi.application.port.out.liga.FindLigaUsuarioPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.liga.Liga;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Order(3)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LigaAdapterIntegrationTest {


    @Autowired
    private CreateLigaPort createLigaPort;
    @Autowired
    private FindLigaPort findLigaPort;
    @Autowired
    private FindAllLigasPort findAllLigasPort;
    @Autowired
    private FindLigaUsuarioPort findLigaUsuarioPort;

    private static Liga created;

    @Test
    @Order(1)
    public void createLigaTest(){
        Liga liga= Liga.builder()
                .nombre("TestLiga")
                .usuario(new UsuarioId(1))
                .build();

        created=createLigaPort.createLiga(liga)
                .orElseThrow();

        Assertions.assertNotEquals(created,null);
        Assertions.assertTrue(created.getLigaId().value()>0);
    }

    @Test
    @Order(2)
    public void findLigaTest(){
        Liga temp=findLigaPort.findLiga(created.getLigaId().value())
                .orElseThrow();

        Assertions.assertNotEquals(temp,null);
        Assertions.assertEquals(temp.getNombre(),created.getNombre());
        Assertions.assertEquals(created.getUsuario().value(),temp.getUsuario().value());
    }

    @Test
    @Order(3)
    public void findAllLigasTest(){
        List<Liga> ligas=findAllLigasPort.findAllLigas();

        Assertions.assertFalse(ligas.isEmpty());
    }

}
