package com.debateia.domain;

public class Score {
    private Integer aiId;
    private Integer combatId;

    public boolean equals(Score sci) {
        return (aiId.equals(sci.aiId) && combatId.equals(sci.combatId));
    }
}
