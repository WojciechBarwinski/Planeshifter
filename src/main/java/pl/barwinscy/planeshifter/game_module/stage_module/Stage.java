package pl.barwinscy.planeshifter.game_module.stage_module;


public abstract class Stage {

    Scene mainScene;
    Scene actualScene;


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
}
