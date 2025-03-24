//package com.alia.back_end_service.api_rest.user;
//
//import com.alia.back_end_service.domain.user.ports.UserPortAPI;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Validated
//@RequestMapping("/users")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserPortAPI userPortAPI;
//
//
//    @PostMapping("/register")
//    public ResponseEntity<UserDTOGet> registerUser(@Valid @RequestBody UserDTOPost userDTOPost) {
//        UserDTOGet registeredUser = userPortAPI.registerUser(userDTOPost);
//        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
//    }
//
//    // De momento no devolvemos el token, cuando lo hagamos en vez de devolver un userDTOGet hay que devolver un DTO nuevo que incluya el token
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
//        UserDTOGet authenticatedUser = userPortAPI.loginUser(loginDTO);
//        return ResponseEntity.ok("Hola");
//    }
//
//}
