package pl.barwinscy.planeshifter.game_module;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.barwinscy.planeshifter.game_module.stage_module.Dialogue;
import pl.barwinscy.planeshifter.game_module.stage_module.InnStage;
import pl.barwinscy.planeshifter.game_module.stage_module.Scene;
import pl.barwinscy.planeshifter.game_module.stage_module.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Component
public class Game {

    private final String GLOBAL_CONDITION = "[#";
    private final String STAGE_CONDITION = "[$";
    private final String GLOBAL_ACTION = "(#";
    private final String STAGE_ACTION = "($";

    private String testValue = "Tekst z game.class";
    private Stage actualStage;
    private Integer gold;
    private List<Stage> stages;

    public Game() {
        stages = new ArrayList<>();
        Scene scene1 = new Scene("Wchodzisz do zadbanej gospody, w środku jest ciepło, pachnie dobrym jedzeniem a w końcie sali przygrywa bard. Za ladą stoi rosły karczmarz.", 0);
        Dialogue dialogue1 = new Dialogue("[$IS_FEED = FALSE]Potrzebuje ciepłego posiłku");
        Dialogue dialogue2 = new Dialogue("[$HAVE_ROOM = FALSE]Czy są wolne pokojne? Muszę gdzies przenocować");
        Dialogue dialogue3 = new Dialogue("[$KNOW_ABOUT_JOB = FALSE]Czy w miasteczku była by jakaś praca?");
        Dialogue dialogue10 = new Dialogue("[$HAVE_ROOM = TRUE, KNOW_ABOUT_JOB = TRUE]Pójdę już spać a rano udam się do ratusza. Dobranoc.(GOTO_STAGE:2)");
        scene1.setDialogueList(Arrays.asList(dialogue1, dialogue2, dialogue3, dialogue10));

        Scene scene2 = new Scene("Obecnie mamy wolny malutki pokój za 15sz oraz apartament dla bogatych przejezdnych kupców za 30sz. Jeśli to Ci nie pasuje zawsze możesz wyspać się tutaj przy kominku.", 1);
        dialogue2.setNextScene(scene2);
        Dialogue dialogue4 = new Dialogue("Poproszę w takim razie ten mały pokój za 15sz(#PAID:15)($HAVE_ROOM = TRUE)");
        Dialogue dialogue5 = new Dialogue("Wezmę apartament");
        Dialogue dialogue6 = new Dialogue("Prześpie się więc tutaj przy kominku($HAVE_ROOM = TRUE)");
        scene2.setDialogueList(Arrays.asList(dialogue4, dialogue5, dialogue6));
        scene2.setPreviousSceneId(0);

        Scene scene3 = new Scene("To już nie byle wydatek, aby na pewno masz tyle pieniędzy?", 2);
        dialogue5.setNextScene(scene3);
        Dialogue dialogue7 = new Dialogue("[#GOLD>30]Tak, proszę(#PAID:15)($HAVE_ROOM = TRUE)");
        Dialogue dialogue8 = new Dialogue("[#GOLD<30]Nie, wybacz, źle przeliczyłem");
        Dialogue dialogue9 = new Dialogue("Jednak się rozmyśliłem");
        scene3.setDialogueList(Arrays.asList(dialogue7, dialogue8, dialogue9));
        scene3.setPreviousSceneId(1);


        Stage innStage = new InnStage(scene1);
        innStage.setSceneList(Arrays.asList(scene1, scene2, scene3));
        stages.add(innStage);
        actualStage = innStage;
        gold = 33;
    }

    public void checkDialogueActionByGame(String dialogue) {
        if (dialogue.contains(STAGE_ACTION)) {
            actualStage.checkDialogueActionByStage(dialogue);
        }
        if (dialogue.contains(GLOBAL_ACTION)){
            if (dialogue.contains("PAID")) {
                paid(dialogue);
            }
        }
    }

    public void prepareActualSceneByGame() {
        Scene actualScene = actualStage.getActualScene();
        clearActualDialogList(actualScene);
        List<Dialogue> dialogueList = actualScene.getDialogueList();

        for (Dialogue tmpDialogue : dialogueList) {
            if (tmpDialogue.getDialogueValue().contains(STAGE_CONDITION)) {
                if (actualStage.checkDialogueConditionByStage(tmpDialogue)) {
                    actualScene.addDialogueToActualDialogueList(tmpDialogue);
                }
            } else if (tmpDialogue.getDialogueValue().contains(GLOBAL_CONDITION)) {
                if (checkDialogueConditionByGame(tmpDialogue)) {
                    actualScene.addDialogueToActualDialogueList(tmpDialogue);
                }
            } else actualScene.addDialogueToActualDialogueList(tmpDialogue);
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
        if (dialogueValue.contains("GOLD>")){
            return (gold > getGoldValue(dialogueValue));
        }else if (dialogueValue.contains("GOLD<")){
            return (gold < getGoldValue(dialogueValue));
        }
        return true;
    }

    private void paid(String dialogue){
        Pattern pattern = Pattern.compile("PAID:([0-9]+)");
        Matcher matcher = pattern.matcher(dialogue);
        if (matcher.find()){
            gold -= Integer.parseInt(matcher.group(1));
        }
    }

    private Integer getGoldValue(String dialogue){
        Pattern pattern = Pattern.compile("GOLD\\W([0-9]+)");
        Matcher matcher = pattern.matcher(dialogue);
        if (matcher.find()){
            return Integer.valueOf(matcher.group(1));
        }
        return 0;
    }
}
