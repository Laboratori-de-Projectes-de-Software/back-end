package com.adondeband.back_end_adonde_band.jpa.usuario;

import com.adondeband.back_end_adonde_band.dominio.usuario.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUsuarioMapper {

    @Autowired
    UsuarioJpaMapper usuarioJpaMapper;

    @Test
    public void comprobarUsuarioEntityToUsuario() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre("nombre");
        usuarioEntity.setCorreo("correo");
        usuarioEntity.setContrasena("contrasena");

        Usuario usuario = usuarioJpaMapper.toDomain(usuarioEntity);

        assertEquals(usuarioEntity.getNombre(), usuario.getNombre());
        assertEquals(usuarioEntity.getCorreo(), usuario.getCorreo());
        assertEquals(usuarioEntity.getContrasena(), usuario.getContrasena());
    }
}
