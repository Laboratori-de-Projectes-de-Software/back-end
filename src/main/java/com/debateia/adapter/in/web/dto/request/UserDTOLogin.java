package com.debateia.adapter.in.web.dto.request;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class UserDTOLogin implements Serializable {
    private String user;
    private String password;
}