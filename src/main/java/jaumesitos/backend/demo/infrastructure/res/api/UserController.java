package jaumesitos.backend.demo.infrastructure.res.api;


import jaumesitos.backend.demo.infrastructure.res.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.RequiredArgsConstructor;

import jaumesitos.backend.demo.infrastructure.res.mapper.UserDTOMapper;
import jaumesitos.backend.demo.application.service.UserService;

@RequiredArgsConstructor
@RestController

@RequestMapping("")
@Tag(name = "User Controller", description = "Endpoints for managing users")
public class UserController {

    private final UserDTOMapper mapper; //convertidor de DTO a classe de lògica de negoci
    private final UserService service; //adaptador

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
    //http://localhost:9090/swagger-ui/index.html#/

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>("Endpoint /api/users/getallUsers", HttpStatus.ACCEPTED);
    }
}
