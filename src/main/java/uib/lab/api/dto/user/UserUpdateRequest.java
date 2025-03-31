package uib.lab.api.dto.user;

import uib.lab.api.validation.password.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class UserUpdateRequest {

    @Email
    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @Password
    @NotBlank
    private String password;
}
