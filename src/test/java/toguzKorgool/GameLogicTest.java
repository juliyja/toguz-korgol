package toguzKorgool;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameLogicTest {
    @org.junit.Test
    public void getInstance() {
        assertEquals(GameLogic.getInstance(),GameLogic.getInstance());
    }

    @org.junit.Test
    public void move() {
    }

    @org.junit.Test
    public void randomMove() {
    }

    @Test
    public void settingStateToRunning(){
        GameLogic instance = GameLogic.getInstance();
        instance.setStateToRunning();
        assertEquals(instance.getState(), GameState.RUNNING);
    }

}
