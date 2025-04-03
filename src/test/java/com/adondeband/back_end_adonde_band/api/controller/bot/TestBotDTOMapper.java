package com.adondeband.back_end_adonde_band.api.controller.bot;

import com.adondeband.back_end_adonde_band.API.bot.BotDTO;
import com.adondeband.back_end_adonde_band.API.bot.BotDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBotDTOMapper {
    @Autowired
    private BotDtoMapper botDtoMapper;

    @Test
    public void comprobarBotDTOToBot() {
        //Arrange
        BotDTO botDTO = new BotDTO(
                "Pepe",
                "Valentía",
                "https://www.zooplus.es/magazine/wp-content/uploads/2018/04/fotolia_169457098.jpg",
                null,
                0,
                0,
                0);

        // Act
        Bot bot = botDtoMapper.toDomain(botDTO);

        // Assert
        assertEquals(botDTO.getNombre(), bot.getNombre().value());
        assertEquals(botDTO.getCualidad(), bot.getCualidad());
        assertEquals(botDTO.getImagen(), bot.getImagen().getRuta());
        assertEquals(botDTO.getEndpoint(), bot.getEndpoint());
    }

    @Test
    public void comprobarBotToBotDTO() {
        // Arrange
        Bot bot = new Bot();
        bot.setNombre(new BotId("Bot_1"));
        bot.setCualidad("Cualidad 1");
        bot.setImagen(new Imagen("https://www.zooplus.es/magazine/wp-content/uploads/2018/04/fotolia_169457098.jpg\",\n"));
        bot.setNumVictorias(0);
        bot.setNumEmpates(0);
        bot.setNumDerrotas(0);
        bot.setUsuario(new UsuarioId("Pepe"));

        // Act
        BotDTO botDTO = botDtoMapper.toDTO(bot);

        // Assert
        assertEquals(bot.getNombre().value(), botDTO.getNombre());
        assertEquals(bot.getCualidad(), botDTO.getCualidad());
        assertEquals(bot.getImagen().getRuta(), botDTO.getImagen());
    }
}
