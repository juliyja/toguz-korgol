package toguzKorgool;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameLogicTest {
    @Test
    public void settingStateToRunning(){
        GameLogic instance = GameLogic.getInstance();
        instance.setStateToRunning();
        assertEquals(instance.getState(), GameState.RUNNING);
    }

}
