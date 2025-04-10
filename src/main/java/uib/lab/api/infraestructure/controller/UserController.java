package uib.lab.api.infraestructure.controller;

import uib.lab.api.application.dto.user.UserResponseDTO;
import uib.lab.api.application.dto.user.UserUpdateRequest;
import uib.lab.api.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uib.lab.api.infraestructure.util.ApiResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;

    @PutMapping("/user/{id}")
    public ApiResponse updateUser(@PathVariable int id, @Valid @RequestBody UserUpdateRequest user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable int id) {
        return userService.findById(id);
    }
}
