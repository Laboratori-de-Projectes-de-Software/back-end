package com.example.gironetaServer.application.usecases.users;

import com.example.gironetaServer.domain.League;

public interface CreateLeague {
    League createLeague(League league);
}