package pl.barwinscy.planeshifter.login_module.controllers;


import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.dtos.NewUserDto;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;
import pl.barwinscy.planeshifter.login_module.validators.NewUserValidator;

@RestController
@RequestMapping("/")
public class NewUserController {

    private UserDetailsServiceImpl userService;
    private NewUserValidator validator;

    public NewUserController(UserDetailsServiceImpl userService, NewUserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @PostMapping
    public UserDto newUser(@Validated @RequestBody UserDto UserDto, BindingResult bindingResult){
        return userService.createUser(UserDto);
    }
}
