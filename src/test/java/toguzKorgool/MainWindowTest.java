package toguzKorgool;

import javax.swing.*;
import static org.junit.Assert.*;
import org.junit.Test;
import com.athaydes.automaton.Swinger;

public class MainWindowTest {
    @Test
    public void testNormalUse() {
        startApp();
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(250)
                .clickOn("name:singlePlayer")
                .pause(250);
    }

    public void startApp(){
        try {
            new MainWindow();
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
    }
}