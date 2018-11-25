package toguzKorgool;

public class GameLogic {

    private static GameLogic instance;

    private GameLogic() {

    }

    public static GameLogic getInstance() {
        if(instance == null){
            instance = new GameLogic();
        }
        return instance;
    }

    public void move(){

    }
}
