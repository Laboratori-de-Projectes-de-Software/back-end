package com.adondeband.back_end_adonde_band.dominio.bot;

public record BotId (Long value){

    @Override
    public String toString() {
        return "BotId{" +
                "value=" + value +
                '}';
    }
}
