package toguzKorgool;

import static org.junit.Assert.*;

import javafx.embed.swing.JFXPanel;
import org.junit.BeforeClass;
import org.junit.Test;


public class AIPlayerTest {

    @BeforeClass
    public static void init(){
        new JFXPanel();
        new FileEditor(null);
        new PlayerBoard();
    }


    @Test
    public void testTuzMove(){
        FileEditor.reinitializeFileEditor("tuzMoveTest");
        PlayerBoard.reinitializeBoard();

        AIPlayer.move();
        assertEquals(1, ((Hole)PlayerBoard.getButtons().get(7)).getKorgools());


    }

    @Test
    public void testNonTuzWithoutTuzMove(){
        FileEditor.reinitializeFileEditor("nonTuzNoTuzMoveTest");
        new PlayerBoard();
        PlayerBoard.reinitializeBoard();

        AIPlayer.move();
        assertEquals(1, ((Hole)PlayerBoard.getButtons().get(7)).getKorgools());
    }

}
