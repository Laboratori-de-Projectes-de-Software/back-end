package com.adondeband.back_end_adonde_band.jpa.participacion;

import com.adondeband.back_end_adonde_band.dominio.participacion.Participacion;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaEntity;
import com.adondeband.back_end_adonde_band.jpa.liga.LigaJpaMapper;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestParticipacionMapper {
    @Autowired
    private ParticipacionJpaMapper participacionJpaMapper;

    @Autowired
    private BotJpaMapper botJpaMapper;

    @Autowired
    private LigaJpaMapper ligaJpaMapper;

    @Test
    public void comprobarParticipacionEntityVoidToDomain() {
        // Arrange
        ParticipacionEntity p_e_void = new ParticipacionEntity();
        ParticipacionEntity p_e = new ParticipacionEntity();

        // Act
        Participacion p_void = participacionJpaMapper.toDomain(p_e_void);

        // Assert
    }

    @Test
    public void comprobarParticipacionEntityToDomain() {
        // Arrange
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre("Usuario 1");

        BotEntity botEntity = new BotEntity();
        botEntity.setNombre("Bot_1");
        botEntity.setCualidad("Cualidad 1");
        botEntity.setUsuario(usuarioEntity);

        LigaEntity ligaEntity = new LigaEntity();
        ligaEntity.setId(1L);
        ligaEntity.setNombre("Liga 1");
        ligaEntity.setFechaInicio(LocalDateTime.now());
        ligaEntity.setFechaFin(LocalDateTime.now());

        ParticipacionEntity p_e = new ParticipacionEntity();
        p_e.setBot(botEntity);
        p_e.setLiga(ligaEntity);

        // Act
        Participacion p= participacionJpaMapper.toDomain(p_e);

        // Assert
        // Assert Participacion
        assertEquals(p_e.getId(), p.getId());
        assertEquals(p_e.getPosicion(), p.getPosicion());
        assertEquals(p_e.getPuntuacion(), p.getPuntuacion());
        // Assert Bot
        assertEquals(p_e.getBot().getNombre(), p.getBot().value());
        // Assert Liga
        assertEquals(p_e.getLiga().getId().longValue(), p.getLiga().value());


    }
}
