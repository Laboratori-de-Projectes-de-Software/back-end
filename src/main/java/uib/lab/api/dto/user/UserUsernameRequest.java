package uib.lab.api.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserUsernameRequest {
    @NotBlank
    private String username;
}
