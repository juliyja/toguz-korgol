package toguzKorgool;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameStateTest {
    @Test
    public void isRunningStateFatal(){
        assertFalse(GameState.RUNNING.getIfFatal());
    }

    @Test
    public void isP1WonStateFatal(){
        assertFalse(GameState.P1WON.getIfFatal());
    }

    @Test
    public void isP2WonStateFatal(){
        assertFalse(GameState.P2WON.getIfFatal());
    }

    @Test
    public void isDrawStateFatal(){
        assertFalse(GameState.DRAW.getIfFatal());
    }

    @Test
    public void isEmptyHoleStateFatal(){
        assertTrue(GameState.EMPTYHOLE.getIfFatal());
    }

}
