package com.example.back_end_eing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private String token;
    private LocalDateTime expiresIn;
    private String user;
    private int userId;
}
