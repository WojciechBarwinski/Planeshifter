package pl.barwinscy.planeshifter.game_module.stage_module;

import lombok.Data;

import java.util.List;

@Data
public class Scene{

    private Integer sceneId;
    private String description;
    private List<Dialogue> dialogueList;
    private Integer previousSceneId;

    public Scene(String description, Integer sceneId) {
        this.description = description;
        this.sceneId = sceneId;
    }
}
