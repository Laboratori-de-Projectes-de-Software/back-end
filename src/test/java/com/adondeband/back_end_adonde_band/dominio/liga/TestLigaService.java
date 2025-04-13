package com.adondeband.back_end_adonde_band.dominio.liga;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.estado.ESTADO;
import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionService;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLigaService {

    @Autowired
    private LigaService ligaService;
    @Autowired
    private BotService botService;
    @Autowired
    private ParticipacionService participacionService;

    @Test
    @Transactional
    public void testSaveLiga() {
        // Arrange
        Liga liga = new Liga();
        liga.setNombre("La liga EA Sports");
        liga.setEstado(ESTADO.PENDIENTE);
        liga.setFechaInicio(LocalDateTime.now());
        liga.setFechaFin(LocalDateTime.now());
        liga.setImagen(null);
        liga.setParticipaciones(new ArrayList<>());
        liga.setUsuario(null);
        liga.setMatchTime(13);
        liga.setRondas(5);

        // Act
        Liga ligaSaved = ligaService.crearLiga(liga);

        // Assert
        assertNull(liga.getId());
        assertNotNull("El id de la liga no debe ser null", ligaSaved.getId());
        assertEquals(liga.getNombre(), ligaSaved.getNombre());
        assertEquals(liga.getEstado(), ligaSaved.getEstado());
        assertEquals(liga.getFechaInicio(), ligaSaved.getFechaInicio());
        assertEquals(liga.getFechaFin(), ligaSaved.getFechaFin());
        assertEquals(liga.getMatchTime(), ligaSaved.getMatchTime());
        assertEquals(liga.getRondas(), ligaSaved.getRondas());

    }

    @Test
    @Transactional
    public void testGetligaById() {
        // Arrange
        Liga liga = new Liga();
        liga.setNombre("La liga EA Sports");
        liga.setEstado(ESTADO.PENDIENTE);
        liga.setFechaInicio(LocalDateTime.now());
        liga.setFechaFin(LocalDateTime.now());
        liga.setImagen(null);
        liga.setParticipaciones(new ArrayList<>());

        // save league
        Liga ligaSaved = ligaService.crearLiga(liga);

        assertNotNull("El id de la liga no debe ser null", ligaSaved.getId());

        LigaId id = ligaSaved.getId();

        // Act
        List<Liga> ligasFound = ligaService.obtenerLigaPorId(id);

        // Assert
        assert (!ligasFound.isEmpty());
        assertEquals(ligaSaved.getId().value(), ligasFound.getFirst().getId().value());
        assertEquals(ligaSaved.getNombre(), ligasFound.getFirst().getNombre());
    }

    @Test
    @Transactional
    public void testGetAllLigas() {
        // Arrange
        for (int i = 1; i <= 5; i++) {
            Liga liga = new Liga();
            liga.setNombre("Liga " + i);
            liga.setEstado(ESTADO.PENDIENTE);
            liga.setFechaInicio(LocalDateTime.now());
            liga.setFechaFin(LocalDateTime.now());
            liga.setImagen(null);
            liga.setParticipaciones(new ArrayList<>());

            ligaService.crearLiga(liga);
        }

        // Act
        List<Liga> ligasFound = ligaService.obtenerTodasLasLigas();

        // Assert
        assert (ligasFound.size() == 5);
        assertEquals("Liga 1", ligasFound.getFirst().getNombre());
    }

    @Test
    @Transactional
    public void testGetLigasByUser() {
        //TODO
    }

    @Test
    @Transactional
    public void testAddBotLiga() {
        // Arrange
        Liga liga = new Liga();
        liga.setId(new LigaId(1L));
        liga.setNombre("La liga EA Sports");
        liga.setEstado(ESTADO.PENDIENTE);
        liga.setFechaInicio(LocalDateTime.now());
        liga.setFechaFin(LocalDateTime.now());
        liga.setImagen(null);
        liga.setParticipaciones(new ArrayList<>());

        // save league
        Liga laliga = ligaService.crearLiga(liga);

        assertNotNull("El id de la liga no debe ser null", laliga.getId());

        Bot bot = new Bot();
        bot.setNombre(new BotId("Bot 1"));

        Bot bot1 = botService.crearBot(bot);

        assertNotNull("El id del bot no debe ser null", bot1.getNombre());   // Act
        Participacion pGuardada = participacionService.insertarParticipacion(new Participacion(bot1.getNombre().value(), laliga.getId().value()));

        assertEquals(pGuardada.getBot(), bot1.getNombre());
    }
}
