package pl.barwinscy.planeshifter.login_module.controllers;


import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/homepage")
public class UserController {

    private UserDetailsServiceImpl userService;

    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto helloPage(Principal principal){
        return userService.getUserByName(principal.getName());
    }

    @PutMapping("/{userName}")
    public UserDto updateUser(@PathVariable String userName,@RequestBody List<String> password){
        return userService.changePassword(userName, password);
    }

}
