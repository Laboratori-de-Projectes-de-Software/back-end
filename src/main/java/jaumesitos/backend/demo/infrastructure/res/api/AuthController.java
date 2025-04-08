package jaumesitos.backend.demo.infrastructure.res.api;


import jaumesitos.backend.demo.domain.User;
import jaumesitos.backend.demo.infrastructure.res.dto.BotDTO;
import jaumesitos.backend.demo.infrastructure.res.dto.UserDTOLogin;
import jaumesitos.backend.demo.infrastructure.res.dto.UserResponseDTO;
import jaumesitos.backend.demo.infrastructure.res.mapper.BotDTOMapper;
import org.springframework.web.bind.annotation.RequestBody;
import jaumesitos.backend.demo.application.service.AuthService;
import jaumesitos.backend.demo.infrastructure.res.dto.UserDTORegister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import jaumesitos.backend.demo.infrastructure.res.mapper.UserDTOMapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController

@RequestMapping("")
@Tag(name = "User Controller", description = "Endpoints for managing users")
public class AuthController {

    private final UserDTOMapper userMapper; //convertidor de DTO a classe de lògica de negoci
    private final AuthService service; //adaptador

    //CODIS ERROR:
    //HttpStatus.OK -> 200
    //HttpStatus.CREATED -> 201
    //HttpStatus.BAD_REQUEST -> 400
    //HttpStatus.UNAUTHORIZED -> 401
    //HttpStatus.NOT_FOUND -> 404
    //HttpStatus.NOT_FOUND -> 404
    //HttpStatus.REQUEST_TIMEOUT -> 408
    //HttpStatus.CONFLICT -> 409
    //HttpStatus.PAYLOAD_TOO_LARGE -> 413
    //HttpStatus.INTERNAL_SERVER_ERROR - 500
    //HttpStatus.GATEWAY_TIMEOUT -> 504

    //SWAGGER:
    //http://localhost:8080/swagger-ui/index.html#/

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>("Endpoint /api/users/getallUsers", HttpStatus.ACCEPTED);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody UserDTORegister dto) {
        try {
            User user = userMapper.toDomain(dto);
            service.register(user);
            return ResponseEntity.ok("User created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserDTOLogin dto) {
        try {
            String token = service.login(dto.getEmail(), dto.getPassword());
            User user = service.getUserByEmail(dto.getEmail());

            UserResponseDTO response = userMapper.toResponseDTO(user);
            response.setToken(token);
            response.setExpiresIn(LocalDate.now().plusDays(1));

            Map<String, Object> result = new HashMap<>();
            result.put("message", "User logged");
            result.put("user", response);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
}
