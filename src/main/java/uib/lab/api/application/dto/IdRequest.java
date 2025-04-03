package uib.lab.api.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IdRequest {
    @NotNull
    private long id;
}
