package com.adondeband.back_end_adonde_band.jpa.conversacion;

import com.adondeband.back_end_adonde_band.dominio.bot.Bot;
import com.adondeband.back_end_adonde_band.dominio.conversacion.Conversacion;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.enfrentamiento.EnfrentamientoEntity;
import com.adondeband.back_end_adonde_band.jpa.imagen.ImagenJpaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConversacionMapper {

    @Autowired
    private ConversacionJpaMapper conversacionJpaMapper;

    @Test
    public void comprobarConversacionEntityToConversacion() {
        // Arrange
        ConversacionEntity conversacionEntity = new ConversacionEntity();
        conversacionEntity.setId(1L);
        conversacionEntity.setFicheroRuta("ficheroRuta");
        conversacionEntity.setEnfrentamiento(new EnfrentamientoEntity());

        // Act
        Conversacion conversacion = conversacionJpaMapper.toDomain(conversacionEntity);

        // Assert
        assertEquals(conversacionEntity.getId(), conversacion.getId());
        assertEquals(conversacionEntity.getFicheroRuta(), conversacion.getFicheroRuta());




    }

}
