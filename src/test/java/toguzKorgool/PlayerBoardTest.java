package toguzKorgool;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class PlayerBoardTest {

    @Test
    public void test() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            latch.countDown();
        });
        latch.await();


        FileEditor editor = new FileEditor("default");
        FXApp.startApp( new PlayerBoard() );
        FXer fxer = FXer.getUserWith( FXApp.getScene().getRoot() );
        sleep(1000);

        fxer.clickOn( "#Button11" );
        sleep(1000);
        assertEquals(PlayerBoard.getButtons().get(11).getKorgools(), 1);
        sleep(1000);
    }


}