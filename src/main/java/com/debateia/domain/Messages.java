package com.debateia.domain;

import lombok.Builder;

import java.util.Date;

@Builder
public class Messages {
    private String contents;
    private Date timestamp;
    private AI ai;
    private Combat combat;


}
