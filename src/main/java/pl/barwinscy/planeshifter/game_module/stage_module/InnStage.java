package pl.barwinscy.planeshifter.game_module.stage_module;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InnStage extends Stage {


    private final Scene MAIN_SCENE;
    private Scene actualScene;
    private boolean isFeed;
    private boolean haveRoom;
    private boolean knowAboutJob;
    List<Scene> sceneList = new ArrayList<>();

    public InnStage(Scene mainScene) {
        this.MAIN_SCENE = mainScene;
        this.actualScene = mainScene;
        isFeed = false;
        haveRoom = false;
        knowAboutJob = false;
    }

    @Override
    public void checkDialogueActionByStage(String dialogue) {
        if (dialogue.contains("HAVE_ROOM = TRUE")) {
            haveRoom = true;
        }
        if (dialogue.contains("IS_FEED = TRUE")) {
            isFeed = true;
        }
        if (dialogue.contains("KNOW_ABOUT_JOB = TRUE")) {
            knowAboutJob = true;
        }
    }

    @Override
    public boolean checkDialogueConditionByStage(Dialogue dialogue) {
        String dialogueValue = dialogue.getDialogueValue();
        if (dialogueValue.contains("HAVE_ROOM") && dialogueValue.contains("KNOW_ABOUT_JOB")) {
            return (haveRoom && knowAboutJob);
        } else if (dialogueValue.contains("HAVE_ROOM")) {
            return !haveRoom;
        } else if (dialogueValue.contains("IS_FEED")) {
            return !isFeed;
        } else if (dialogueValue.contains("KNOW_ABOUT_JOB")) {
            return !knowAboutJob;
        }
        return false; //TODO coś z tym zrobić
    }
}
