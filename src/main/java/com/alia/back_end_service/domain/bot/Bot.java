package com.alia.back_end_service.domain.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URI;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bot {
    private String name;

    private String description;

    private URI endpoint;

    private String token;

    private String userId;

    private List<Integer> leagueIds;

}
