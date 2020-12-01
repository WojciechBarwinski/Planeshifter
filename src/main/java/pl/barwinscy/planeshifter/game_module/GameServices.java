package pl.barwinscy.planeshifter.game_module;

import org.springframework.stereotype.Service;
import pl.barwinscy.planeshifter.game_module.stage_module.Dialogue;
import pl.barwinscy.planeshifter.game_module.stage_module.Scene;
import pl.barwinscy.planeshifter.game_module.stage_module.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GameServices {

    final private Game GAME;


    public GameServices(Game GAME) {
        this.GAME = GAME;
    }

    public SceneDTO getActualScene(){
        prepareActualSceneByGame();
        Scene actualScene = GAME.getActualStage().getActualScene();
        return sceneToSceneDTO(actualScene);
    }

    public void setActualScene(Integer dialogueId) {
        Scene actualScene = GAME.getActualStage().getActualScene();
        String chosenDialogue = actualScene.getDialogueList().get(dialogueId).getDialogueValue();

        if (chosenDialogue.contains("($") || chosenDialogue.contains("(#")){
            checkDialogueAction(chosenDialogue);
        }
        if (!chosenDialogue.contains("(BACK)")){
            setNextScene(dialogueId);
        } else {
            setBackScene();
        }
    }

    private void setNextScene(Integer dialogueId){
        Stage actualStage = GAME.getActualStage();
        Scene nextScene = actualStage.getActualScene().getDialogueList().get(dialogueId).getNextScene();
        if (nextScene == null){
            Scene mainScene = actualStage.getMAIN_SCENE();
            actualStage.setActualScene(mainScene);
        } else {
            actualStage.setActualScene(nextScene);
        }
    }

    private void setBackScene(){
        Stage actualStage = GAME.getActualStage();
        Scene previousScene = actualStage.getSceneList().get(actualStage.getActualScene().getPreviousSceneId());
        actualStage.setActualScene(previousScene);
    }

    private void prepareActualSceneByGame() {
        String GLOBAL_CONDITION = "[#";
        String STAGE_CONDITION = "[$";
        Scene actualScene = GAME.getActualStage().getActualScene();
        clearActualDialogList(actualScene);
        List<Dialogue> dialogueList = actualScene.getDialogueList();

        for (Dialogue tmpDialogue : dialogueList) {
            if (tmpDialogue.getDialogueValue().contains(STAGE_CONDITION)) {
                if (GAME.getActualStage().checkDialogueConditionByStage(tmpDialogue)) {
                    actualScene.addDialogueToActualDialogueList(tmpDialogue);
                }
            } else if (tmpDialogue.getDialogueValue().contains(GLOBAL_CONDITION)) {
                if (checkDialogueConditionByGame(tmpDialogue)) {
                    actualScene.addDialogueToActualDialogueList(tmpDialogue);
                }
            } else actualScene.addDialogueToActualDialogueList(tmpDialogue);
        }
    }

    private void checkDialogueAction(String dialogue) {
        String STAGE_ACTION = "($";
        String GLOBAL_ACTION = "(#";
        if (dialogue.contains(STAGE_ACTION)) {
            GAME.getActualStage().checkDialogueActionByStage(dialogue);
        }
        if (dialogue.contains(GLOBAL_ACTION)) {
            if (dialogue.contains("PAID")) {
                paidGold(dialogue);
            }
        }
    }

    private void clearActualDialogList(Scene actualScene) {
        if (actualScene.getActualDialogueList() == null) {
            actualScene.setActualDialogueList(new ArrayList<>());
        } else {
            actualScene.getActualDialogueList().clear();
        }
    }

    private boolean checkDialogueConditionByGame(Dialogue dialogue) {
        String dialogueValue = dialogue.getDialogueValue();
        if (dialogueValue.contains("GOLD>")) {
            return (GAME.getGold() > getGoldValue(dialogueValue));
        } else if (dialogueValue.contains("GOLD<")) {
            return (GAME.getGold() < getGoldValue(dialogueValue));
        }
        return true;
    }

    private void paidGold(String dialogue) {
        String forGold = "PAID:([0-9]+)";
        Pattern pattern = Pattern.compile(forGold);
        Matcher matcher = pattern.matcher(dialogue);
        if (matcher.find()) {
            GAME.setGold(GAME.getGold() - Integer.parseInt(matcher.group(1)));
        }
    }

    private Integer getGoldValue(String dialogue) {
        String forGold = "GOLD\\W+([0-9]+)";
        Pattern pattern = Pattern.compile(forGold);
        Matcher matcher = pattern.matcher(dialogue);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        return 0;
    }

    private SceneDTO sceneToSceneDTO(Scene actualScene){
        SceneDTO sceneDTO = new SceneDTO();
        if (actualScene.getDescription() != null){
            sceneDTO.setDescription(actualScene.getDescription());
        }
        List<Dialogue> dialogueList = actualScene.getActualDialogueList();
        for (Dialogue dialogue : dialogueList) {
            sceneDTO.setDialogues(cutDialogue(dialogue.getDialogueValue()));
        }
        return sceneDTO;
    }

    private String cutDialogue(String fullDialogue){
        String regexToCutDialogue = "@(.+)@";
        Pattern pattern = Pattern.compile(regexToCutDialogue);
        Matcher matcher = pattern.matcher(fullDialogue);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return fullDialogue;
    }
}
