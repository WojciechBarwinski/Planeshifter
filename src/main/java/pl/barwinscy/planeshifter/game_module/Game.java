package pl.barwinscy.planeshifter.game_module;

import lombok.Data;
import org.springframework.stereotype.Component;
import pl.barwinscy.planeshifter.game_module.stage_module.Stage;

import java.util.List;

@Data
@Component
public class Game {

    private String testValue = "Tekst z game.class";

    private List<Stage> stages;

    

}
