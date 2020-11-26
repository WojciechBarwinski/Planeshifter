package pl.barwinscy.planeshifter.game_module;

import org.springframework.stereotype.Service;
import pl.barwinscy.planeshifter.game_module.stage_module.Dialogue;
import pl.barwinscy.planeshifter.game_module.stage_module.Scene;
import pl.barwinscy.planeshifter.game_module.stage_module.Stage;

import java.util.List;

@Service
public class GameServices {

    final private Game GAME;

    public GameServices(Game GAME) {
        this.GAME = GAME;
    }

    public SceneDTO getActualScene(){
        GAME.prepareActualSceneByGame();
        Scene actualScene = GAME.getActualStage().getActualScene();
        return sceneToSceneDTO(actualScene);
    }

    public void setActualScene(Integer dialogueId) { //TODO ogarnąć tą metode
        Stage actualStage = GAME.getActualStage();
        Scene actualScene = actualStage.getActualScene();

        String chosenDialogue = actualScene.getDialogueList().get(dialogueId).getDialogueValue(); //wybrany dialog
        if (chosenDialogue.contains("($") || chosenDialogue.contains("(#")){
            GAME.checkDialogueActionByGame(chosenDialogue);
        }
        if (!chosenDialogue.contains("(BACK)")){
            Scene nextScene = actualScene.getDialogueList().get(dialogueId).getNextScene();
            if (nextScene == null){
                Scene mainScene = actualStage.getMAIN_SCENE();
                actualStage.setActualScene(mainScene);
            } else {
                actualStage.setActualScene(nextScene);
            }
        } else {
            Scene previousScene = actualStage.getSceneList().get(actualScene.getPreviousSceneId());
            actualStage.setActualScene(previousScene);
        }
    }

    private SceneDTO sceneToSceneDTO(Scene actualScene){
        SceneDTO sceneDTO = new SceneDTO();
        if (actualScene.getDescription() != null){
            sceneDTO.setDescription(actualScene.getDescription());
        }
        List<Dialogue> dialogueList = actualScene.getActualDialogueList();
        for (Dialogue dialogue : dialogueList) {
            sceneDTO.setDialogues(dialogue.getDialogueValue());
        }
        return sceneDTO;
    }
}
