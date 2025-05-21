package com.debateia.adapter.in.rest.match;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class MessageDTO implements Serializable {
    private int botId;
    private String text;
    private String timestamp;
}
