package toguzKorgool;

import javafx.embed.swing.JFXPanel;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AIPlayerTest {

    @BeforeClass
    public static void init(){
        new JFXPanel();

    }


    @Test
    public void testTuzMove(){
        FileEditor.reinitializeFileEditor("tuzMoveTest");
        //PlayerBoard.reinitializeBoard();
        new AIPlayer();
        GameLogic.getInstance().refreshHoles();

        AIPlayer.move();
        assertEquals(1, (PlayerBoard.getButtons().get(7)).getKorgools());


    }

    @Test
    public void testNonTuzWithoutTuzMove(){
        FileEditor.reinitializeFileEditor("nonTuzNoTuzMoveTest");
        //PlayerBoard.reinitializeBoard();
        GameLogic.getInstance().refreshHoles();
        new AIPlayer();

        AIPlayer.move();
        assertEquals(1, (PlayerBoard.getButtons().get(7)).getKorgools());
    }

    @Test
    public void testNonTuzWithTuzMove(){
        FileEditor.reinitializeFileEditor("nonTuzWithTuzMoveTest");
        //PlayerBoard.reinitializeBoard();
        GameLogic.getInstance().refreshHoles();
        PlayerBoard.getButtons().get(6).makeTuz();
        new AIPlayer();

        AIPlayer.move();
        assertEquals(1, PlayerBoard.getButtons().get(7).getKorgools());
    }

}