package pl.barwinscy.planeshifter.game_module.controllers;


import org.springframework.web.bind.annotation.*;
import pl.barwinscy.planeshifter.game_module.GameServices;
import pl.barwinscy.planeshifter.game_module.SceneDTO;


@RestController
@RequestMapping("/api/v1/newGame")
public class GameController {

    final private GameServices GAME_SERVICES;

    public GameController(GameServices gameServices) {
        this.GAME_SERVICES = gameServices;
    }


    @GetMapping("/game")
    public SceneDTO getStages(){
        return GAME_SERVICES.getActualScene();
    }

/*    @PostMapping("/test3/{dialogueId}")
    public SceneDTO getActualScene(@PathVariable String dialogueId){
        gameServices.setActualScene(Integer.valueOf(dialogueId));
        return gameServices.getActualScene();
    }*/

    @PostMapping("/game")
    public SceneDTO getActualScene2(@RequestBody Integer dialogueId){
        GAME_SERVICES.setActualScene(dialogueId);
        return GAME_SERVICES.getActualScene();
    }
}
