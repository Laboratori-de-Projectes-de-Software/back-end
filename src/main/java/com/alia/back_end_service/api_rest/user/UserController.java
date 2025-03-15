package com.alia.back_end_service.api_rest.user;

import com.alia.back_end_service.domain.user.UserPortAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserPortAPI userPortAPI;

    @PostMapping("/register")
    public ResponseEntity<UserDTOGet> registerUser(@RequestBody UserDTOPost userDTOPost) {
        UserDTOGet registeredUser = userPortAPI.registerUser(userDTOPost);
        return ResponseEntity.ok(registeredUser);
    }


}
