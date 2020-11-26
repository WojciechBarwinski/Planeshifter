package pl.barwinscy.planeshifter.game_module.stage_module;

import lombok.Data;

import java.util.List;

@Data
public class Scene{

    private Integer sceneId;
    private String description;
    private List<Dialogue> dialogueList;
    private List<Dialogue> actualDialogueList;
    private Integer previousSceneId;

    public Scene(String description, Integer sceneId) {
        this.description = description;
        this.sceneId = sceneId;
    }

    public void addDialogueToActualDialogueList(Dialogue dialogue){
        actualDialogueList.add(dialogue);
    }
}
