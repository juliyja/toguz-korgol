package toguzKorgool;
import static org.junit.Assert.*;

import javafx.embed.swing.JFXPanel;
import org.junit.BeforeClass;
import org.junit.Test;

public class PCPlayerTest {
    @BeforeClass
    public static void init(){
        new JFXPanel();

    }

    @Test
    public void testNonTuzWithoutTuzMove(){
        new FileEditor("nonTuzNoTuzMoveTest");
        new PlayerBoard();
        GameLogic.getInstance().refreshHoles();
        new AIPlayer();

        AIPlayer.move();
        assertEquals(1, (PlayerBoard.getButtons().get(7)).getKorgools());
    }

    @Test
    public void testNonTuzWithTuzMove(){
        new FileEditor("nonTuzWithTuzMoveTest");
        new PlayerBoard();
        GameLogic.getInstance().refreshHoles();
        PlayerBoard.getButtons().get(6).makeTuz();
        new AIPlayer();

        AIPlayer.move();
        assertEquals(1, PlayerBoard.getButtons().get(7).getKorgools());
    }
}
