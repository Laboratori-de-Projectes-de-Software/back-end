package com.debateia.adapter.in.web.dto.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class UserResponseDTO implements Serializable {
    private String token;
    private LocalDate expiresIn;
    private String user;
    private int userId;
}
