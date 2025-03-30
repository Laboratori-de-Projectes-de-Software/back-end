package com.alia.back_end_service.domain.user.ports;

import com.alia.back_end_service.domain.league.League;

import java.util.List;

public interface GetAllUserLeaugesPortAPI {
    List<League> GetAllUserLeauges(String username);
}
