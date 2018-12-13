package toguzKorgool;

import static org.junit.Assert.*;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import org.junit.Test;

public class AIPlayerTest {
    @Test
    public void testTuzMove(){
        new FileEditor(false,"tuzMoveTest");
        new Player_Board();
        FXApp.initializeIfStageExists();
        if (FXApp.isInitialized()) {
            System.out.println("Testing");
            FXer fxer = FXer.getUserWith( FXApp.getScene().getRoot() );
            AIPlayer ai = new AIPlayer();
            ai.move();
            assertEquals(((Hole)Player_Board.getButtons().get(7)).getKorgools(), 1);

        } else {
            throw new RuntimeException( "Could not find a JavaFX Stage" );
        }
    }
}
