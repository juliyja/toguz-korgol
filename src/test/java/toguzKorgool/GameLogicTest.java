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
    public void move() {

            new FileEditor("tuzMoveTest");
            new PlayerBoard();
            //PlayerBoard.reinitializeBoard();

            GameLogic.getInstance().move(11);
            assertEquals(1, ((Hole)PlayerBoard.getButtons().get(11)).getKorgools());

    }

    @org.junit.Test
    public void randomMove() {
    }

    @Test
    public void settingStateToRunning(){
        GameLogic instance = GameLogic.getInstance();
        instance.setStateToRunning();
        assertEquals(GameState.RUNNING, instance.getState());
    }

}
