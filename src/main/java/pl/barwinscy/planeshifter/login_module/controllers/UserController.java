package pl.barwinscy.planeshifter.login_module.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.CustomError;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.exceptions.UserNotMatchedException;
import pl.barwinscy.planeshifter.login_module.exceptions.UsernameIsTakenException;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;
import pl.barwinscy.planeshifter.login_module.validators.UserValidator;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/homepage")
public class UserController {

    private final UserDetailsServiceImpl userService;
    private final UserValidator validator;


    public UserController(UserDetailsServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.validator = userValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping
    public UserDto helloPage(Principal principal){
        return userService.getUserByName(principal.getName());
    }

    @PutMapping()
    public UserDto updateUser(Principal principal, @Validated @RequestBody UserDto userDto, BindingResult bindingResult){
        if (!principal.getName().equals(userDto.getUsername())){
            throw new UsernameIsTakenException(new CustomError("UserDto", "user.validation.incorrectUsername", "You can only change Your account data"));
        }
        if (bindingResult.hasErrors()){
            throw new UserNotMatchedException(bindingResult);
        }
        return userService.updateUser(userDto);
    }

    @DeleteMapping()
    public String deleteUser(Principal principal){
        userService.deleteUser(principal.getName());
        return "Konto usunięte, dziękujemy";
    }
}
