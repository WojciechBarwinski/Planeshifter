package pl.barwinscy.planeshifter.login_module.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.services.RegistrationService;
import pl.barwinscy.planeshifter.login_module.Dtos.UserDto;


@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerNewUser(@RequestBody UserDto userDto){
        return registrationService.createUser(userDto);
    }

}
