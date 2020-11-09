package pl.barwinscy.planeshifter.login_module.dtos;

import lombok.Data;
import pl.barwinscy.planeshifter.login_module.enums.UserRole;

@Data
public class UserDto {


    private String username;
    private PasswordDto password;
    private UserRole role;
}
