package org.example.backend.databaseapi.application.dto.mensaje;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class MessageDTOResponse {

    private String text;
    private Integer botId;
    private String time;

}
