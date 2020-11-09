package pl.barwinscy.planeshifter.login_module.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.exceptions.UserNotMatchedException;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private UserDetailsServiceImpl userService;

    public AdminController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerNewUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable String userId, @Validated @RequestBody UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new UserNotMatchedException(bindingResult);
        }
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);

        return "Konto usunięte, dziękujemy";
    }
}
