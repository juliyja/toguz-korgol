package toguzKorgool;

public enum GameState {
    RUNNING     (false, "Game is Running"),
    P1WON       (false, "Player 1 won!"),
    P2WON       (false, "Player 2 won!"),
    DRAW        (false, "It's a Draw"),
    EMPTYHOLE   (true, "This hole is empty");

    boolean isFatal;
    String description;

    GameState(boolean isFatal, String description){
        this.isFatal = isFatal;
        this.description = description;
    }

    public boolean getIfFatal(){
        return isFatal;
    }

    public String getDescription(){
        return description;
    }
}
