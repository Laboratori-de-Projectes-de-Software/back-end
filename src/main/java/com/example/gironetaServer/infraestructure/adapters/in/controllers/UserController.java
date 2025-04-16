package com.example.gironetaServer.infraestructure.adapters.in.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gironetaServer.application.services.UserService;
import com.example.gironetaServer.domain.User;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.dto.UserDto;
import com.example.gironetaServer.infraestructure.adapters.in.controllers.mappers.UserMapper;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v0/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserResponse> usersResponse = users.stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(usersResponse);
    }

    // GET /user/1 -> user(1)
    @GetMapping("/api/v0/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponse userResponse = UserMapper.toUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    // POST /user -> user(4)
    @PostMapping("/api/v0/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = UserMapper.toAppObject(userDto);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(UserMapper.toUserDto(createdUser));
    }

    @GetMapping("/api/v0/user/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponse userResponse = UserMapper.toUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/api/v0/user/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponse userResponse = UserMapper.toUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }
}
