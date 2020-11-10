package pl.barwinscy.planeshifter.login_module.controllers;

import org.springframework.http.HttpStatus;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1/administration")
public class AdminController {

    private final UserDetailsServiceImpl userService;
    private final UserValidator validator;

    public AdminController(UserDetailsServiceImpl userService, UserValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    //ANOTHER USER ACCOUNT
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public UserDto newUser(@Validated @RequestBody UserDto UserDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new UserNotMatchedException(bindingResult);
        }
        return userService.createUser(UserDto);
    }

    @PutMapping("/user")
    public UserDto updateUser(@Validated @RequestBody UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new UserNotMatchedException(bindingResult);
        }
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable String userId){
        userService.deleteUserByAdmin(userId);
        return "Konto usunięte, dziękujemy";
    }


    //ADMIN ACCOUNT
    @GetMapping("/admin")
    public UserDto getAdmin(Principal principal){
        return userService.getUserByName(principal.getName());
    }

    @PutMapping("/admin")
    public UserDto updateAdmin(Principal principal, @Validated @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (!principal.getName().equals(userDto.getUsername())){
            throw new UsernameIsTakenException(new CustomError("UserDto", "user.validation.incorrectUsername", "You can only change Your account data"));
        }
        if (bindingResult.hasErrors()) {
            throw new UserNotMatchedException(bindingResult);
        }
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/admin")
    public String deleteUser(Principal principal){
        userService.deleteUser(principal.getName());
        return "Konto usunięte, dziękujemy";
    }







}
