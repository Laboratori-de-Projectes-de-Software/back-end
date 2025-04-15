package com.adondeband.back_end_adonde_band.dominio.participacion;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestParticipacionService {

    @Autowired
    private LigaService ligaService;
    @Autowired
    private BotService botService;
    @Autowired
    private ParticipacionService participacionService;

    @Test
    @Transactional
    public void testAddBotToLiga() {
        // Arrange
        Liga liga = new Liga();
//        liga.setId(new LigaId(1L));
        liga.setNombre("La liga EA Sports");
        liga.setEstado(ESTADO.PENDIENTE);
        liga.setFechaInicio(LocalDateTime.now());
        liga.setFechaFin(LocalDateTime.now());
        liga.setImagen(null);

        Bot bot1 = new Bot("Bot 1", "Cualidad1");

        Participacion participacion = new Participacion();
        System.out.println("Participacion: " + participacion.getId() + "\n\n\n\n\n\n");
        participacion.setBot(bot1.getId());
        participacion.setLiga(liga.getId());
        participacion.setPosicion(1);
        participacion.setPuntuacion(100);

        // Participacion participacion2 = new Participacion();
        // participacion2.setBot(bot1.getNombre());

        // Act
        // Guardar el bot y la liga para que tengan un id (como se haría en un caso real)
        botService.crearBot(bot1);
        ligaService.crearLiga(liga);
        Participacion participacionSaved = participacionService.insertarParticipacion(participacion);

        // Assert
        assertNull(liga.getId());
        assertNotNull("El id de la participación no debe ser null", participacionSaved.getId());
        assertEquals(participacion.getBot(), participacionSaved.getBot());
        assertEquals(participacion.getLiga(), participacionSaved.getLiga());
    }
}
