package com.adondeband.back_end_adonde_band.dominio.bot;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBotService {

    @Autowired
    private BotService botService;

    @Test
    public void testSaveBot() {
        // Arrange
        Bot bot = new Bot("Bot1", "Cualidad1");

        // Act
        Bot botSaved = botService.crearBot(bot);

        // Assert
        assertEquals(bot.getNombre(), botSaved.getNombre());
        assertEquals(bot.getCualidad(), botSaved.getCualidad());
    }

    @Test
    @Transactional
    public void testGetBotByName() {
        // Arrange
        Bot bot = new Bot("Bot10", "Cualidad1");
        botService.crearBot(bot);

        // Act
        Bot botFound = botService.obtenerBotPorNombre("Bot10");

        // Assert
        assertEquals(bot.getNombre(), botFound.getNombre());
        assertEquals(bot.getCualidad(), botFound.getCualidad());
    }

    @Test
    @Transactional
    public void testGetAllBots() {
        // Arrange
        for (int i = 1; i <= 5; i++) {
            Bot bot = new Bot( "Bot " + i, "Cualidad " + i);
            botService.crearBot(bot);
        }

        // Act
        List<Bot> botsFound = botService.obtenerTodosLosBots();

        // Assert
        assert (botsFound.size() == 5);
        assertEquals("Bot 1", botsFound.getFirst().getNombre());
        assertEquals("Cualidad 1", botsFound.getFirst().getCualidad());
    }

    @Test
    @Transactional
    public void testGetBotsByUser() {
        //TODO
    }


    @Test
    @Transactional
    public void testUniqueBotName() {
        // Arrange
        Bot bot1 = new Bot("Bot1", "Cualidad1");
        Bot bot2 = new Bot("Bot1", "Cualidad2"); // Mismo nombre

        // Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            botService.crearBot(bot1);
            botService.crearBot(bot2);
        });

        // Assert
        assertEquals("El nombre del bot ya existe", e.getMessage());
    }

}
