package org.example.backend.databaseapi.application.controller.usuario;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
import org.example.backend.databaseapi.domain.usuario.TokenAndUsuario;
=======
import org.example.backend.databaseapi.domain.usuario.AuthResponse;
import org.example.backend.databaseapi.application.dto.usuario.UserDTOLogin;
import org.example.backend.databaseapi.application.dto.usuario.UserDTORegister;
import org.example.backend.databaseapi.application.dto.usuario.UsuarioDTOMapper;
>>>>>>> 31e71e3 (small fix)
import org.example.backend.databaseapi.application.exception.IncorrectCredentialsException;
import org.example.backend.databaseapi.application.port.in.usuario.ActualizarUsuarioPort;
import org.example.backend.databaseapi.application.port.in.usuario.AltaUsuarioPort;
import org.example.backend.databaseapi.application.port.in.usuario.BuscarUsuarioPort;
import org.example.backend.databaseapi.application.port.in.usuario.EliminarUsuarioPort;
import org.example.backend.databaseapi.application.service.JwtService;
import org.example.backend.databaseapi.application.service.PasswordService;
import org.example.backend.databaseapi.domain.usuario.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class UsuarioController {

    private final AltaUsuarioPort altaUsuarioPort;
    private final BuscarUsuarioPort buscarUsuarioPort;
    private final EliminarUsuarioPort eliminarUsuarioPort;
    private final UserModelAssembler userModelAssembler;
    private final ActualizarUsuarioPort actualizarUsuarioPort;
    private final PasswordService passwordService;
    private final UsuarioDTOMapper usuarioDTOMapper;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<TokenAndUsuario> register(@RequestBody Usuario request) {
        Usuario user = altaUsuarioPort.altaUsuario(request);
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(TokenAndUsuario.builder()
                .token(token)
                .usuario(user)
                .build());
    }

    @GetMapping("/usuario/{id}")
    ResponseEntity<EntityModel<Usuario>> buscarUsuario(@PathVariable Integer id){
        Usuario user = buscarUsuarioPort.buscarUsuario(id);
        return ResponseEntity.ok(userModelAssembler.toModel(user));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenAndUsuario> login(@RequestBody Usuario request) {
        String email=null;
        if(request.getEmail()!=null){
            email=request.getEmail().value();
        }
        Usuario user = buscarUsuarioPort.buscarUsuario(email);
        // damos el mismo mensaje de error en ambos casos para no proporcionar más información de la necesaria
        if (user == null || !passwordService.matchesPassword(request.getPassword(), user.getPassword())) {
            throw new IncorrectCredentialsException("El email o la contraseña son incorrectos");
        }

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(TokenAndUsuario.builder()
                .token(token)
                .usuario(user.getNombre())
                .userId(user.getUserId().value())
                .expiresIn(jwtService.extractExpiration(token))
                .build());
    }

    @DeleteMapping("/usuario/{id}")
    ResponseEntity<?> eliminarUsuario(@PathVariable Integer id){
        eliminarUsuarioPort.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/usuario/{id}")
    ResponseEntity<EntityModel<Usuario>> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable Integer id){
        Usuario user = actualizarUsuarioPort.actualizarUsuario(usuario, id);
        return ResponseEntity.created(linkTo(methodOn(UsuarioController.class).buscarUsuario(user.getUserId().value())).toUri())
                .body(userModelAssembler.toModel(user));
    }
}
