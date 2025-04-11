package com.adondeband.back_end_adonde_band.jpa.bot;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBotMapper {

    @Autowired
    private BotJpaMapper botJpaMapper;

    @Test
    public void comprobarBotEntityToBotSencillo() {
        // Arrange
        BotEntity botEntity = new BotEntity();
        botEntity.setNombre("Bot_1");
        botEntity.setCualidad("Cualidad 1");

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre("Usuario 1");
        usuarioEntity.setId(1L);
        botEntity.setUsuario(usuarioEntity);

        // Act
        Bot bot = botJpaMapper.toDomain(botEntity);

        // Assert
        assertEquals(bot.getNombre().value(), botEntity.getNombre());
        assertEquals(bot.getCualidad(), botEntity.getCualidad());;
        assertEquals(bot.getUsuario().value(), botEntity.getUsuario().getId());
    }
}