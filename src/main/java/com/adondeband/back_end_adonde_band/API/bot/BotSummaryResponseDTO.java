package com.adondeband.back_end_adonde_band.API.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BotSummaryResponseDTO{

    private String name;

    private String id;

    private String description;
}
