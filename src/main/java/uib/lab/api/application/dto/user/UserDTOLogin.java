package uib.lab.api.application.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTOLogin {

    @NotBlank
    private String user;

    @NotBlank
    private String password;
}
