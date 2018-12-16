package toguzKorgool;



import com.athaydes.automaton.SwingerFxer;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertNotEquals;

public class BoardTest {

    static JFrame jFrame;

    static JFXPanel jfxPanel;

    @Test

    public void test() throws InterruptedException {
        new MainWindow();

        final CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {

            new JFXPanel();

            latch.countDown();

        });

        latch.await();

        new FileEditor("default");

        jFrame = PlayerBoard.getInstance().getFrame();

        //jfxPanel = PlayerBoard.getInstance().getFrame();



        sleep(5000);


        SwingerFxer swfx = SwingerFxer.getUserWith( PlayerBoard.getFrame(), PlayerBoard.getPanel().getScene().getRoot());

        sleep(1000);


        int number = PlayerBoard.getButtons().get(17).getKorgools();
        swfx.clickOn("Button17");

        sleep(250);

        assertNotEquals(number, PlayerBoard.getButtons().get(17).getKorgools());

        sleep(250);

        number = PlayerBoard.getButtons().get(16).getKorgools();


        swfx.clickOn("Button16");

        sleep(250);

        assertNotEquals(number, PlayerBoard.getButtons().get(16).getKorgools());

        sleep(250);


        PlayerBoard.getFrame().dispose();
    }
}