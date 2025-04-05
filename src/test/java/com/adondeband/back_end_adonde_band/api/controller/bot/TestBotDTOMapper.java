package com.adondeband.back_end_adonde_band.api.controller.bot;

import com.adondeband.back_end_adonde_band.API.bot.BotDTO;
import com.adondeband.back_end_adonde_band.API.bot.BotDTOMin;
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
    BotDtoMapper botDtoMapper;

    @Test
    public void botToBotDTO() {
        Bot bot = new Bot(new BotId("Bot2"),
                        0,
                        0,
                        0,
                        "Cualidad1",
                        "endpoint.com",
                        new UsuarioId("Pepe1"),
                        new Imagen("imagen.jpg"),
                        null);

        BotDTO botDTO = botDtoMapper.toDTO(bot);

        assert botDTO != null;
        assertEquals(bot.getNombre().value(), botDTO.getNombre());
        assertEquals(bot.getCualidad(), botDTO.getCualidad());
        assertEquals(bot.getEndpoint(), botDTO.getEndpoint());
        assertEquals(bot.getImagen().getRuta(), botDTO.getImagen());
        assertEquals(bot.getNumVictorias(), botDTO.getNumVictorias());
        assertEquals(bot.getNumDerrotas(), botDTO.getNumDerrotas());
        assertEquals(bot.getNumEmpates(), botDTO.getNumEmpates());
    }

    @Test
    public void botDTOToBot() {
        BotDTO botDTO = new BotDTO();
        botDTO.setNombre("Bot2");
        botDTO.setCualidad("Cualidad1");
        botDTO.setEndpoint("endpoint.com");
        botDTO.setNumVictorias(3);
        botDTO.setNumDerrotas(3);
        botDTO.setNumEmpates(3);
        botDTO.setImagen("imagen.jpg");

        Bot bot= botDtoMapper.toDomain(botDTO);

        assert bot != null;
        assertEquals(bot.getNombre().value(), botDTO.getNombre());
        assertEquals(bot.getCualidad(), botDTO.getCualidad());
        assertEquals(bot.getEndpoint(), botDTO.getEndpoint());
        assertEquals(bot.getImagen().getRuta(), botDTO.getImagen());
        assertEquals(bot.getNumVictorias(), botDTO.getNumVictorias());
        assertEquals(bot.getNumDerrotas(), botDTO.getNumDerrotas());
        assertEquals(bot.getNumEmpates(), botDTO.getNumEmpates());
    }

    @Test
    public void BotDTOtoBotDTOMin() {
        BotDTO botDTO = new BotDTO();
        botDTO.setNombre("Bot2");
        botDTO.setCualidad("Cualidad1");
        botDTO.setEndpoint("endpoint.com");
        botDTO.setNumVictorias(3);
        botDTO.setNumDerrotas(3);
        botDTO.setNumEmpates(3);
        botDTO.setImagen("imagen.jpg");

        BotDTOMin botDTOMin = new BotDTOMin(botDTO);

        assertEquals(botDTOMin.getNombre(), botDTO.getNombre());
        assertEquals(botDTOMin.getCualidad(), botDTO.getCualidad());
        assertEquals(botDTOMin.getEndpoint(), botDTO.getEndpoint());
        assertEquals(botDTOMin.getImagen(), botDTO.getImagen());
    }

    @Test
    public void BotDTOMintoBotDTO() {
        BotDTOMin botDTOMin = new BotDTOMin();
        botDTOMin.setNombre("Bot2");
        botDTOMin.setCualidad("Cualidad1");
        botDTOMin.setEndpoint("endpoint.com");
        botDTOMin.setImagen("imagen.jpg");

        BotDTO botDTO = new BotDTO(botDTOMin);

        assertEquals(botDTOMin.getNombre(), botDTO.getNombre());
        assertEquals(botDTOMin.getCualidad(), botDTO.getCualidad());
        assertEquals(botDTOMin.getEndpoint(), botDTO.getEndpoint());
        assertEquals(botDTOMin.getImagen(), botDTO.getImagen());
    }
}
