package com.adondeband.back_end_adonde_band.jpa.conversacion;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBotMapper {
    private BotJpaMapper botJpaMapper;

    @Before
    public void setUp() {
        // Obtener la implementación generada por MapStruct
        botJpaMapper = Mappers.getMapper(BotJpaMapper.class);
    }

    @Test
    public void comprobarBotEntityToBotSencillo() {
        // Arrange
        BotEntity botEntity = new BotEntity();
        botEntity.setNombre("Bot 1");
        botEntity.setCualidad("Cualidad 1");

        // Act
        Bot bot = botJpaMapper.toDomain(botEntity);

        // Assert
        assertEquals(bot.getNombre().value(), botEntity.getNombre());
        assertEquals(bot.getCualidad(), botEntity.getCualidad());

    }
}
