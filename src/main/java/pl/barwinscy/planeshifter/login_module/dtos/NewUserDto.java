package pl.barwinscy.planeshifter.login_module.dtos;

import lombok.Data;
import pl.barwinscy.planeshifter.login_module.enums.UserRole;

@Data
public class NewUserDto {

    private String userName;
    private PasswordDto password;
    private UserRole userRole;
}
