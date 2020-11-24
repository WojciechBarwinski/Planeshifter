package pl.barwinscy.planeshifter.game_module.stage_module;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InnStage extends Stage{

    private Scene mainScene;
    private Scene actualScene;
    private boolean isFeed;
    private boolean haveRoom;
    List<Scene> sceneList = new ArrayList<>();

    public InnStage(Scene mainScene) {
        this.mainScene = mainScene;
        this.actualScene = mainScene;
        isFeed = false;
        haveRoom = false;
    }

    @Override
    public void checkDialogueActionByStage(String dialogue) {
        if (dialogue.contains("HAVE_ROOM = True")){
            haveRoom = true;
        }
        if (dialogue.contains("IS_FEED = True")){
            isFeed = true;
        }
    }

    public Scene getActualSceneByStage(){
        Scene checkedScene = new Scene(actualScene.getDescription(), actualScene.getSceneId());

        return null;
    }
}
