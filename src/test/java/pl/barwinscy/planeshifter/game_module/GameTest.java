package pl.barwinscy.planeshifter.game_module;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;


@SpringBootTest
public class GameTest {


    private static Game game;

    @BeforeAll
    public static void setup(){
        game = new Game();
    }



}