package pl.barwinscy.planeshifter.game_module.stage_module;

import lombok.Data;

@Data
public class InnStage extends Stage{

    private Scene mainScene;
    private Scene actualScene;
    private boolean isFeed;
    private boolean haveRoom;

    public InnStage(Scene mainScene) {
        this.mainScene = mainScene;
        this.actualScene = mainScene;
        isFeed = false;
        haveRoom = false;
    }
}
