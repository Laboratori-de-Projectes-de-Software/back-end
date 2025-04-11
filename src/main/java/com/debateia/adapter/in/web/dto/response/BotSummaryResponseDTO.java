package com.debateia.adapter.in.web.dto.response;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class BotSummaryResponseDTO implements Serializable {
    private String name;
    private int id;
    private String description;
}
