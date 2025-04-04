package com.example.back_end_eing.dto;

import lombok.Builder;
import lombok.Value;
@Builder
@Value
public class UserResponseDTO {
    private String token;
    private long expiresIn;
    private String user;
    private int userId;
}
