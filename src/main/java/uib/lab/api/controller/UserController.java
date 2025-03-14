package uib.lab.api.controller;

import uib.lab.api.dto.user.UserResponse;
import uib.lab.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable long id, Locale locale) {
        return userService.findById(id, locale);
    }
}
