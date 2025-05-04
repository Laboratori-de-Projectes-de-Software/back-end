package com.adondeband.back_end_adonde_band.jpa.liga;


import com.adondeband.back_end_adonde_band.dominio.bot.BotId;
import com.adondeband.back_end_adonde_band.dominio.imagen.Imagen;
import com.adondeband.back_end_adonde_band.dominio.liga.Liga;
import com.adondeband.back_end_adonde_band.dominio.liga.LigaId;
import com.adondeband.back_end_adonde_band.dominio.participacion.ParticipacionId;
import com.adondeband.back_end_adonde_band.dominio.usuario.UsuarioId;
import com.adondeband.back_end_adonde_band.jpa.bot.BotEntity;
import com.adondeband.back_end_adonde_band.jpa.bot.BotJpaRepository;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioEntity;
import com.adondeband.back_end_adonde_band.jpa.usuario.UsuarioJpaRepository;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LigaJpaAdapterTest {

    @Autowired
    private LigaJpaAdapter ligaJpaAdapter;

    @Autowired
    private LigaJpaRepository ligaJpaRepository;

    @Autowired
    private LigaJpaMapper ligaJpaMapper;
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    @Autowired
    private BotJpaRepository botJpaRepository;


    @Before
    @Transactional
    public void setUp() {
        System.out.println("Setting up the test...");
        // Clean up the repository before each test
        ligaJpaRepository.deleteAll();

        // Add test data if necessary
        LigaEntity ligaEntity = new LigaEntity();
        ligaEntity.setNombre("Liga 1");
        ligaEntity.setFechaInicio(LocalDateTime.now());
        ligaEntity.setFechaFin(LocalDateTime.now());
        ligaJpaRepository.save(ligaEntity);

        LigaEntity ligaEntity2 = new LigaEntity();
        ligaEntity2.setNombre("Liga 2");
        ligaEntity2.setFechaInicio(LocalDateTime.now());
        ligaEntity2.setFechaFin(LocalDateTime.now());
        ligaJpaRepository.save(ligaEntity2);

        BotEntity botEntity1 = new BotEntity("Bot 1", "Bot 1 cualidad");
        botJpaRepository.save(botEntity1);
        BotEntity botEntity2 = new BotEntity("Bot 2", "Bot 2 cualidad");
        botJpaRepository.save(botEntity2);

        //Usuarios
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre("Usuario Test");
        usuarioEntity.setContrasena("Penetrador");
        usuarioEntity.setCorreo("pepe123@pepe.com");

        usuarioJpaRepository.save(usuarioEntity);

        //Usuario 2
        UsuarioEntity usuarioEntity2 = new UsuarioEntity();
        usuarioEntity2.setNombre("Usuario Test 2");
        usuarioEntity2.setContrasena("Penetrador");
        usuarioEntity2.setCorreo("pepe123@pepe.com");

        usuarioJpaRepository.save(usuarioEntity2);

    }

    @Test
    @Transactional
    public void testSaveSencillo() {
        Liga liga = new Liga();
        liga.setNombre("Liga Test");
        liga.setFechaInicio(LocalDateTime.now());
        liga.setFechaFin(LocalDateTime.now());
        liga.setUsuario(new UsuarioId(1L));
        Liga savedLiga = ligaJpaAdapter.save(liga);
        assertNotNull(savedLiga);

        assertEquals(liga.getNombre(), savedLiga.getNombre());
        assertEquals(liga.getFechaInicio(), savedLiga.getFechaInicio());
        assertEquals(liga.getFechaFin(), savedLiga.getFechaFin());
        assertNotNull(savedLiga.getId());

    }

    @Test
    @Transactional
    public void testSaveContrato(){
        Liga liga = new Liga();
        liga.setNombre("Liga Test");
        liga.setImagen(new Imagen("test.png"));
        liga.setRondas(5);
        liga.setMatchTime(30L);
        liga.setUsuario(new UsuarioId(1L));

        Liga ligaGuardada = ligaJpaAdapter.save(liga);

        ligaJpaAdapter.addBotToLiga(ligaGuardada.getId(),new BotId(1L));
        ligaJpaAdapter.addBotToLiga(ligaGuardada.getId(),new BotId(2L));

        Liga ligaActualizada = ligaJpaAdapter.findById(ligaGuardada.getId());

        assertEquals(ligaGuardada.getNombre(), ligaActualizada.getNombre());
        assertEquals(ligaGuardada.getImagen(), ligaActualizada.getImagen());
        assertEquals(ligaGuardada.getRondas(), ligaActualizada.getRondas());
        assertEquals(ligaGuardada.getMatchTime(), ligaActualizada.getMatchTime());
        assertEquals(ligaGuardada.getParticipaciones().size(), ligaActualizada.getParticipaciones().size());
        assertEquals(ligaGuardada.getParticipaciones().getFirst(), ligaActualizada.getParticipaciones().getFirst());
    }



}