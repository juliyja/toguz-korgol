package toguzKorgool;

import com.athaydes.automaton.Swinger;
import javafx.application.Platform;
import org.junit.Test;


public class MainWindowTest {
    MainWindow window;

    public MainWindowTest(){
        try {
            window = new MainWindow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSinglePlayer() {
        testButton("singlePlayer");
        testButton("loadGame");
        testButton("easyGame");
        testButton("mediumGame");
        testButton("hardGame");
        testButton("instructions");
        Platform.exit();
    }


    private void testButton(String buttonName){
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(5000).clickOn("name:" + buttonName).pause(1000);
        PlayerBoard.getFrame().hide();
    }


}

