package pl.barwinscy.planeshifter.login_module.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.dtos.PasswordDto;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.exceptions.UserNotMatchedException;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;
import pl.barwinscy.planeshifter.login_module.validators.PasswordValidator;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/homepage")
public class UserController {

    private UserDetailsServiceImpl userService;
    private PasswordValidator validator;


    public UserController(UserDetailsServiceImpl userService, PasswordValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping
    public UserDto helloPage(Principal principal){
        return userService.getUserByName(principal.getName());
    }

    @PutMapping("/{userName}")
    public UserDto updateUser(@PathVariable String userName, @Validated @RequestBody PasswordDto password, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new UserNotMatchedException(bindingResult);
        }
        return userService.changePassword(userName, password);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerNewUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);

        return "Konto usunięte, dziękujemy";
    }
}
