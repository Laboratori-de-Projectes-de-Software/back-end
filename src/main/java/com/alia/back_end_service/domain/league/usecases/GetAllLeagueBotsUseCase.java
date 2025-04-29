package com.alia.back_end_service.domain.league.usecases;

import com.alia.back_end_service.domain.bot.Bot;
import com.alia.back_end_service.domain.bot.port.BotPortDB;
import com.alia.back_end_service.domain.league.ports.LeagueGetAllBotsPortAPI;
import com.alia.back_end_service.domain.league.ports.LeaguePortDB;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetAllLeagueBotsUseCase implements LeagueGetAllBotsPortAPI {
    private LeaguePortDB leaguePortDB;

    @Override
    public List<Bot> getAllLeagueBots(Integer league_id) {
        return leaguePortDB.getAllLeagueBots(league_id);
    }
}
