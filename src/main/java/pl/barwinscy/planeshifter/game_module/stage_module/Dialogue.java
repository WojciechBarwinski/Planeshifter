package pl.barwinscy.planeshifter.game_module.stage_module;

import lombok.Data;

@Data
public class Dialogue {

    private String dialogueValue;
    private Scene nextScene;
}
