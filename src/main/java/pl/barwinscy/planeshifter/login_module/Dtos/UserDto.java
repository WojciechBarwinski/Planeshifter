package pl.barwinscy.planeshifter.login_module.Dtos;

import lombok.Data;
import pl.barwinscy.planeshifter.login_module.enums.UserRole;

@Data
public class UserDto {

    private String username;
    private String password;
    private UserRole role;
}
