package pl.barwinscy.planeshifter.game_module;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SceneDTO {

    private String description;
    private List<String> dialogues = new ArrayList<>();

    public void setDialogues(String dialogues) {
        this.dialogues.add(dialogues);
    }
}
