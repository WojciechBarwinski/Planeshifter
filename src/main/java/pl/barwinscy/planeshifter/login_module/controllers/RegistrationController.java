package pl.barwinscy.planeshifter.login_module.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;
import pl.barwinscy.planeshifter.login_module.services.RegistrationService;
import pl.barwinscy.planeshifter.login_module.UserDto;

import javax.management.relation.Role;


@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;
    private UserDetailsServiceImpl userService;

    public RegistrationController(RegistrationService registrationService, UserDetailsServiceImpl userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerNewUser(@RequestBody UserDto userDto){
        return registrationService.createUser(userDto);
    }

}
