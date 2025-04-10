package com.alia.back_end_service.domain.game;

import com.alia.back_end_service.domain.bot.Bot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {

    private Integer id;

    private OffsetDateTime timestamp;

    private String result_local;

    private String result_visit;

    private String bot_local_name;

    private String bot_visit_name;

    private Integer roundId;

    private List<Integer> messageIds = new ArrayList<>();

    private String state;

}