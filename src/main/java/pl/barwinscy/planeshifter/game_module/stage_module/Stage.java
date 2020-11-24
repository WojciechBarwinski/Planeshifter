package pl.barwinscy.planeshifter.game_module.stage_module;


import java.util.ArrayList;
import java.util.List;

public abstract class Stage {

    Scene mainScene;
    Scene actualScene;
    List<Scene> sceneList = new ArrayList<>();


    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Scene getActualScene() {
        return actualScene;
    }

    public void setActualScene(Scene actualScene) {
        this.actualScene = actualScene;
    }

    public List<Scene> getSceneList() {
        return sceneList;
    }

    public void setSceneList(List<Scene> sceneList) {
        this.sceneList = sceneList;
    }

    abstract public void checkDialogueActionByStage(String dialogue);
}
