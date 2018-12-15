package toguzKorgool;

import com.athaydes.automaton.Swinger;
import org.junit.Test;


public class MainWindowTest {


    MainWindow window;

    public MainWindowTest() {
        try {
            window = new MainWindow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(1000).clickOn("name:" + buttonName).pause(1000);
    }

    private void startApp(){
        try {
            window = new MainWindow();
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
    }

    /*@After
    public void after(){
        Platform.exit();
        window.mainWindowClose();
    }*/
}

