package pl.barwinscy.planeshifter.login_module.controllers;


import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.exceptions.UserNotMatchedException;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;
import pl.barwinscy.planeshifter.login_module.validators.UserValidator;

@RestController
@RequestMapping("/newUser")
public class NewUserController {

    private final UserDetailsServiceImpl userService;
    private final UserValidator validator;

    public NewUserController(UserDetailsServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.validator = userValidator;
    }
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @PostMapping()
    public UserDto newUser(@Validated @RequestBody UserDto UserDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new UserNotMatchedException(bindingResult);
        }
        return userService.createUser(UserDto);
    }
}
