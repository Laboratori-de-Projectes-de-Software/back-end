package org.example.backend.repository;

import org.example.backend.databaseapi.application.port.in.resultado.CrearResultadoPort;
import org.example.backend.databaseapi.application.port.out.bot.*;
import org.example.backend.databaseapi.application.port.out.resultado.FindBotResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindLigaResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindPartidaResultadoPort;
import org.example.backend.databaseapi.application.port.out.resultado.FindResultadoPort;
import org.example.backend.databaseapi.domain.bot.Bot;
import org.example.backend.databaseapi.domain.resultado.Resultado;
import org.example.backend.databaseapi.domain.resultado.ResultadoId;
import org.example.backend.databaseapi.domain.usuario.UsuarioId;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Order(6)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ResultadoAdapterIntegrationTest {

    @Autowired
    private CrearResultadoPort crearResultadoPort;
    @Autowired
    private FindResultadoPort findResultadoPort;
    @Autowired
    private FindLigaResultadoPort findLigaResultadoPort;
    @Autowired
    private FindPartidaResultadoPort findPartidaResultadoPort;
    @Autowired
    private FindBotResultadoPort findBotResultadoPort;

    private static Resultado created;

    @Test
    @Order(1)
    public void createResultadoTest(){
        Resultado resultado= Resultado.builder()
                .resultadoId(new ResultadoId(1,1))
                .puntuacion(3)
                .build();

        created=crearResultadoPort.crearResultado(resultado);

        Assertions.assertNotEquals(created,null);
        Assertions.assertTrue(created.getResultadoId().partidavalue()>0
                && created.getResultadoId().botvalue()>0);
    }

    @Test
    @Order(2)
    public void findResultadoTest(){
        Resultado found=findResultadoPort.findResultado(created.getResultadoId().botvalue(),
                        created.getResultadoId().partidavalue())
                .orElseThrow();

        Assertions.assertNotEquals(found,null);
        Assertions.assertSame(found.getPuntuacion(), created.getPuntuacion());
    }

    @Test
    @Order(3)
    public void findPartidaResultadoTest(){
        List<Resultado> resultados=findPartidaResultadoPort.findPartidaResultado(created.getResultadoId().partidavalue());

        Assertions.assertFalse(resultados.isEmpty());
    }

    @Test
    @Order(4)
    public void findBotResultadoTest(){
        List<Resultado> resultados=findBotResultadoPort.findBotResultados(created.getResultadoId().botvalue());

        Assertions.assertFalse(resultados.isEmpty());
    }
}
