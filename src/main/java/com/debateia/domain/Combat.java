package com.debateia.domain;

import lombok.Builder;

import java.util.List;

@Builder
public class Combat {
    private Match match;
    private List<Messages> msgs;


}
