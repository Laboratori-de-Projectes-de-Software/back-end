package com.adondeband.back_end_adonde_band.dominio.bot;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBotService {

    @Autowired
    private BotService botService;

    @Test
    public void testSaveBot() {
        // Arrange
        Bot bot = new Bot(new BotId("Bot1"), "Cualidad1");

        // Act
        Bot botSaved = botService.crearBot(bot);

        // Assert

        assertEquals(bot.getNombre().value(), botSaved.getNombre().value());
        assertEquals(bot.getCualidad(), botSaved.getCualidad());
    }

    @Test
    @Transactional
    public void testGetBotByName() {
        // Arrange
        Bot bot = new Bot(new BotId("Bot2"), "Cualidad1");
        botService.crearBot(bot);

        // Act
        Bot botFound = botService.obtenerBotPorNombre("Bot2").getFirst();

        // Assert
        assertEquals(bot.getNombre().value(), botFound.getNombre().value());
        assertEquals(bot.getCualidad(), botFound.getCualidad());

    }


    @Test
    @Transactional
    public void testUniqueBotName() {
        // Arrange
        Bot bot1 = new Bot(new BotId("Bot1"), "Cualidad1");
        Bot bot2 = new Bot(new BotId("Bot1"), "Cualidad2");

        // Act
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            botService.crearBot(bot1);
            botService.crearBot(bot2);
        });

        // Assert
        assertEquals("El nombre del bot ya existe", e.getMessage());
    }

}
