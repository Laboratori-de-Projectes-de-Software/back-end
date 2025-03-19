package com.debateia.domain;

import com.debateia.adapter.out.persistence.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Builder
public class AI {
    private String trait;
    private String secret;
    private String endpoint;
    private Boolean enabled = true;
    private UserEntity user;
}
