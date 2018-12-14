package toguzKorgool;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import javafx.embed.swing.JFXPanel;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class GameLogicTest {

    @org.junit.Test
    public void getInstance() {
        assertEquals(GameLogic.getInstance(),GameLogic.getInstance());
    }

    @BeforeClass
    public static void init(){
        new JFXPanel();
    }


@Test
    public void movementTest (){
        moveTest();
        collectEvenTest();
        moveOneKorgoolTest();
        makeTuzTest();
}

    private void moveTest() {

            new FileEditor("simpleMoveTest");
            new PlayerBoard();
            //PlayerBoard.reinitializeBoard();

            GameLogic.getInstance().move(12);
            assertEquals(1, (PlayerBoard.getButtons().get(12)).getKorgools());
            assertEquals(10, (PlayerBoard.getButtons().get(10)).getKorgools());

    }


    private void moveOneKorgoolTest() {

        new FileEditor("oneKorgoolTest");
        new PlayerBoard();
        //PlayerBoard.reinitializeBoard();

        GameLogic.getInstance().move(12);
        assertEquals(0, (PlayerBoard.getButtons().get(12)).getKorgools());
        assertEquals(2, (PlayerBoard.getButtons().get(13)).getKorgools());
    }

    private void collectEvenTest() {
        new FileEditor("collectEvenHole");
        new PlayerBoard();
        //PlayerBoard.reinitializeBoard();

        GameLogic.getInstance().move(13);
        assertEquals(0, (PlayerBoard.getButtons().get(3)).getKorgools());
        assertEquals(20, (PlayerBoard.getButtons().get(10)).getKorgools());
    }

private void makeTuzTest() {
    new FileEditor("collectEvenHole");
    new PlayerBoard();
    //PlayerBoard.reinitializeBoard();

    GameLogic.getInstance().move(19);
    GameLogic.getInstance().move(19);
    assertTrue(PlayerBoard.getButtons().get(1).isTuz());
}


    @Test
    public void settingStateToRunning(){
        GameLogic instance = GameLogic.getInstance();
        instance.setStateToRunning();
        assertEquals(GameState.RUNNING, instance.getState());
    }

}
