package com.adondeband.back_end_adonde_band.dominio.enfrentamiento;

//import com.adondeband.back_end_adonde_band.API.mensaje.MensajeDTO;
//import com.adondeband.back_end_adonde_band.API.mensaje.MensajeDtoMapper;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.dominio.conversacion.ConversacionImpl;
import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.bot.BotService;
import com.adondeband.back_end_adonde_band.dominio.mensajes.Mensajes;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEnfrentamientoService {

    @Autowired
    private EnfrentamientoImpl enfrentamientoService;
    @Autowired
    private ConversacionImpl conversacionService;
    @Autowired
    private BotService botService;

    //private MensajeDtoMapper mensajeDtoMapper;


    @Test
    @Transactional
    public void testGetMensajes() {
        // Arrange
        Bot bot1 = new Bot("Bot1", "Cualidad1");
        botService.crearBot(bot1);
        Bot bot2 = new Bot("Bot2", "Cualidad2");
        botService.crearBot(bot2);
        Conversacion conversacion = new Conversacion();
        conversacion.setFicheroRuta("src/test/java/com/adondeband/back_end_adonde_band/dominio/enfrentamiento/conv.json");

        Enfrentamiento enfrentamiento = new Enfrentamiento();
        enfrentamiento.setLocal(new BotId(1L));
        enfrentamiento.setVisitante(new BotId(2L));
        enfrentamiento.setConversacion(conversacion);

        // Act
        Enfrentamiento enfrentamientoSaved = enfrentamientoService.insertarEnfrentamiento(enfrentamiento);
        Mensajes mensajes = conversacionService.obtenerMensajes(enfrentamientoSaved.getConversacion().getFicheroRuta());

        // Assert
        assert mensajes != null;
        assertEquals(7, (mensajes.getMsgsL().size() + mensajes.getMsgsV().size()));
        assertEquals("Yo creo que la empatía es buena", mensajes.getMsgsL().getFirst().getMensaje());
        assertEquals("Vale tío", mensajes.getMsgsL().getLast().getMensaje());
    }

}
