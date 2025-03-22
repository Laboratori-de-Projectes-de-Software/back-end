package com.alia.back_end_service.domain.game;

import com.alia.back_end_service.domain.bot.Bot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {

    private Integer id;

    private OffsetDateTime timestamp;

    private String result_local;

    private String result_visit;

    private Bot bot_local_id;

    private Bot bot_visit_id;

    private String state;

}