package pl.barwinscy.planeshifter.game_module.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.barwinscy.planeshifter.game_module.Game;
import pl.barwinscy.planeshifter.login_module.dtos.UserDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {

    private Game game;

    public GameController(Game game) {
        this.game = game;
    }

    @GetMapping("/test")
    public String test(){
        return game.getTestValue();
    }
}
