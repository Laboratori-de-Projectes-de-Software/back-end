package com.alia.back_end_service.domain.league.ports;

import com.alia.back_end_service.domain.bot.Bot;

import java.util.List;

public interface LeagueGetAllBotsPortAPI {
    List<Bot> getAllLeagueBots(Integer league_id);
}
