package toguzKorgool;

import com.athaydes.automaton.SwingerFxer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import javax.swing.*;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class PlayerBoardTest {

    static JFrame jFrame;

    static JFXPanel jfxPanel;


    static void setup( ) {

    }

    @Test
    public void test() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            latch.countDown();
        });
        latch.await();
        new FileEditor("default");
        jFrame = PlayerBoard.getInstance().getFrame();
        jfxPanel = PlayerBoard.getInstance().getPanel();

        sleep(5000);
        System.out.println(jFrame.getX());
        System.out.println(jfxPanel.getScene().getRoot());
        SwingerFxer swfx = SwingerFxer.getUserWith( PlayerBoard.getFrame(), PlayerBoard.getPanel().getScene().getRoot());
        sleep(1000);

        swfx.doubleClickOn("Button11");
        sleep(1000);
        assertEquals(PlayerBoard.getButtons().get(11).getKorgools(), 1);
        sleep(2000);

        jFrame.dispose();
        Platform.exit();
    }
}