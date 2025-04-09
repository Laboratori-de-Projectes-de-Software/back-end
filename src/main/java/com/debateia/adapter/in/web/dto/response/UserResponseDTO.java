package com.debateia.adapter.in.web.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    private String token;
    private LocalDate expiresIn;
    private String user;
    private int userId;
}
