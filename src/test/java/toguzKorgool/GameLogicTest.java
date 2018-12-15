package toguzKorgool;

import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class GameLogicTest {

    @org.junit.Test
    public void getInstance() {
        assertEquals(GameLogic.getInstance(),GameLogic.getInstance());
    }

    /*@BeforeClass
    public static void init(){
        new JFXPanel();
    }*/


    @Test
    public void move() {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new FileEditor("tuzMoveTest");
            PlayerBoard.getInstance();

            GameLogic.getInstance().move(11);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, ((Hole)PlayerBoard.getInstance().getButtons().get(11)).getKorgools());

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
