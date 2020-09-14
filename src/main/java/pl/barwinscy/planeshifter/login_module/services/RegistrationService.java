package pl.barwinscy.planeshifter.login_module.services;

import pl.barwinscy.planeshifter.login_module.dtos.UserDto;

public interface RegistrationService {

    UserDto createUser (UserDto userDto);

}
