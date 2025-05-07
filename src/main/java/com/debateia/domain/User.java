package com.debateia.domain;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId; // INTEGERS POR FAVOR ;-;
    private String username;
    private String mail;
    private String password;
    private Integer leagueId;
    private List<Integer> botsId;
    
    private String token;
    private Instant expiresIn;
}
