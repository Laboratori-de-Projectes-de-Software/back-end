package org.example.backend.repository;

import org.example.backend.databaseapi.application.port.out.usuario.CreateUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.FindUsuarioPort;
import org.example.backend.databaseapi.application.port.out.usuario.UpdateUsuarioPort;
import org.example.backend.databaseapi.domain.usuario.Email;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.junit.jupiter.api.*;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Order(1)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserAdapterIntegrationTest {

    @Autowired
    private CreateUsuarioPort createUsuarioPort;
    @Autowired
    private UpdateUsuarioPort updateUsuarioPort;
    @Autowired
    private FindUsuarioPort findUsuarioPort;

    private static Usuario created;

    @Test
    @Order(1)
    public void createUsuarioTest(){
        Usuario user=Usuario.builder()
                .nombre("Test")
                .email(new Email("Test"))
                .password("pass")
                .build();

        created=createUsuarioPort.createUsuario(user)
                .orElseThrow();

        Assertions.assertNotEquals(created,null);
        Assertions.assertTrue(created.getUserId().value() > 0);
    }

    @Test
    @Order(2)
    public void findUsuarioTest(){
        Usuario foundUser=findUsuarioPort.findUsuario(created.getUserId().value())
                .orElseThrow();

        Assertions.assertNotEquals(foundUser,null);
        Assertions.assertEquals(foundUser.getNombre(), created.getNombre());
        Assertions.assertEquals(foundUser.getEmail().value(), created.getEmail().value());
    }

    @Test
    @Order(3)
    public void updateUsuarioTest(){
        Usuario updatedUser=Usuario.builder()
                .nombre("TestUpdated")
                .email(new Email("TestUpdatedMail"))
                .build();

        created=updateUsuarioPort.updateUsuario(updatedUser,created.getUserId().value());

        Assertions.assertNotEquals(updatedUser,null);
        Assertions.assertEquals(updatedUser.getNombre(), created.getNombre());
        Assertions.assertEquals(updatedUser.getEmail().value(), created.getEmail().value());
    }
}
