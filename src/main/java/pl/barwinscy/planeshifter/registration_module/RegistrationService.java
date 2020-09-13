package pl.barwinscy.planeshifter.registration_module;

import pl.barwinscy.planeshifter.registration_module.dto.UserDto;

public interface RegistrationService {

    UserDto createUser (UserDto userDto);

}
