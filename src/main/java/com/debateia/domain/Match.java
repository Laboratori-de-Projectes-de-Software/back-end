package com.debateia.domain;


import com.debateia.adapter.out.persistence.CombatEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {
    private Date date;
    private List<Combat> combats = new ArrayList<>();

}
