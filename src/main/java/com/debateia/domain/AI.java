package com.debateia.domain;

import com.debateia.adapter.out.persistence.UserEntityTEsting;
import lombok.Builder;

@Builder
public class AI {
    private String trait;
    private String secret;
    private String endpoint;
    private Boolean enabled = true;
    private UserEntityTEsting user;
}
