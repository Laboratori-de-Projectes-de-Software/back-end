package jaumesitos.backend.demo.infrastructure.res.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    private String token;
    private LocalDate expiresIn;
    private String user;
    private int userId;
}
