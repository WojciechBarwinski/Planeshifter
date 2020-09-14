package pl.barwinscy.planeshifter.login_module.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.login_module.entities.User;
import pl.barwinscy.planeshifter.login_module.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private UserDetailsServiceImpl userService;

    public AdminController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
