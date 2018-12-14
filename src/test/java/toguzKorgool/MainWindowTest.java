package toguzKorgool;

import javafx.application.Platform;
import org.junit.After;
import org.junit.Test;
import com.athaydes.automaton.Swinger;


public class MainWindowTest {
    @Test
    public void testSinglePlayer() {
        testButton("singlePlayer");
    }

    @Test
    public void testLoadGame() {
        testButton("loadGame");
    }

    @Test
    public void testEasyGame() {
        testButton("easyGame");
    }

    @Test
    public void testMediumGame() {
        testButton("mediumGame");
    }

    @Test
    public void testHardGame() {
        testButton("hardGame");
    }

    @Test
    public void testInstructions() {
        testButton("instructions");
    }

    private void testButton(String buttonName){
        startApp();
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(1000).clickOn("name:" + buttonName).pause(1000);
    }

    private void startApp(){
        try {
            new MainWindow();
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
    }

    @After
    public void after(){
        Platform.exit();
    }
}

