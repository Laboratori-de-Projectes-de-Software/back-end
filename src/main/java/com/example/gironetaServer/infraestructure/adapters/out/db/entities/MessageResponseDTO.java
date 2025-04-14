package com.example.gironetaServer.infraestructure.adapters.out.db.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDTO {

    private String text;
    private Long botId;
    private String time;

}