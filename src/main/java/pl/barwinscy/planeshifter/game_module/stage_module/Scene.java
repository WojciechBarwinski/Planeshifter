package pl.barwinscy.planeshifter.game_module.stage_module;

import lombok.Data;

import java.util.List;

@Data
public class Scene{

    private String description;
    private List<Dialogue> dialogueList;
}
