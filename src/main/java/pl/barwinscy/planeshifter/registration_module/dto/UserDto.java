package pl.barwinscy.planeshifter.registration_module.dto;

import lombok.Data;
import pl.barwinscy.planeshifter.login_module.UserRole;

@Data
public class UserDto {

    private String username;
    private String password;
    private UserRole role;
}
