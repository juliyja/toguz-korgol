package toguzKorgool;

import static org.junit.Assert.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class AIPlayerTest {

    @BeforeClass
    public static void init(){
        new JFXPanel();
    }


    @Test
    public void testTuzMove(){
        new FileEditor("tuzMoveTest");
        new Player_Board();

        AIPlayer.move();
        assertEquals(1, ((Hole)Player_Board.getButtons().get(7)).getKorgools());


    }

    @Test
    public void testNonTuzWithoutTuzMove(){
        new FileEditor("nonTuzNoTuzMoveTest");
        new Player_Board();

        AIPlayer.move();
        assertEquals(1, ((Hole)Player_Board.getButtons().get(7)).getKorgools());
    }

    @After
    public void deleteBoard(){
        Platform.exit();
    }

}
