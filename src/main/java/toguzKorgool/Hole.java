package toguzKorgool;


import javafx.scene.control.Button;

/**
 * A class extending Button to provide the required by the GameLogic and PlayerBoard functionality.
 * The Hole instance has a player, number of korgools and tuz variables, as well as the inherited from Button
 * functionality.
 * Holes with index 0 and 10 are kazans so they do not have action listeners. Holes that are not of the active player
 * (player == true) also do not interact with the user of the application.
 *
 * @author Emiliyana Tsanova
 * @version 1.0
 */
public class Hole extends Button {


    private boolean player;
    private int korgools;
    private boolean tuz = false;

    /**
     * A constructor for the Hole class.
     * Holes at index 0 and 10 and those with player false are not assigned Action listeners.
     *
     * @param player the player "owning" the Hole
     * @param korgools the number of korgools in the Hole
     * @param index the index of the Hole
     */
    Hole(boolean player, int korgools, int index){
        this.korgools = korgools;
        this.player = player;
        if(!(index == 0 || index == 10) && player) {
            this.setOnAction(e -> {
                GameLogic logic = GameLogic.getInstance();
                if(logic.getState() == GameState.RUNNING){
                    logic.move(index);
                    AIPlayer.move();
                } else if(logic.getState() == GameState.EMPTYHOLE){
                    logic.setStateToRunning();
                } else if(logic.getState() == GameState.DRAW){
                    PlayerBoard.gameEndAlert(GameState.DRAW.getDescription());
                } else if(logic.getState() == GameState.P1WON){
                    PlayerBoard.gameEndAlert(GameState.P1WON.getDescription());
                } else if(logic.getState() == GameState.P2WON){
                    PlayerBoard.gameEndAlert(GameState.P2WON.getDescription());
                }
            });
        }
        paintButton();
    }

    /**
     * A method used to update the view of a Hole.
     */
    public void updateHole(){
        this.setText("" + korgools);
        paintButton();
    }

    /**
     * A getter method to return the number of korgools in a Hole.
     *
     * @return the number of korgools in the Hole.
     */
    public int getKorgools(){
        return korgools;
    }

    /**
     * A setter method to change the number of korgools in a Hole.
     * The number needs to be both positive and smaller than 163.
     *
     * @param korgools the new korgools value to be set.
     */
    public void setKorgools(int korgools){
        if(!(korgools < 0 || korgools > 162)){
            this.korgools = korgools;
        }
    }

    /**
     * Reset the number of korgools in a Hole to 0.
     */
    public void clearKorgools(){
        korgools = 0;
    }

    /**
     * Makes a Hole tuz if it is not one already.
     * Per the description of a tuz the player assigned to the Hole is changed to the opposite.
     * To make the change visible to the player the Hole is repainted in the new appropriate color.
     */
    public void makeTuz(){
        if(!tuz){
            tuz = true;
            switchPlayer();
            paintButton();
        }
    }

    /**
     * Checks if a hole is tuz or not.
     *
     * @return the value of the variable tuz.
     */
    public boolean isTuz(){
        return tuz;
    }

    /**
     * A getter method that returns the player "owning" the Hole.
     *
     * @return the value of the player variable.
     */
    public boolean getPlayer(){
        return player; }

    /**
     * Paints the button in the appropriate color based on the player they belong to.
     */
    private void paintButton(){
        if(player){
            setStyle("-fx-background-color: LightGray");
        }
        else {
            setStyle("-fx-background-color: LightBlue");
        }
    }

    /**
     * Assigns the opposite player to the Hole.
     * e.g. (true -> false)
     */
    private void switchPlayer() {
        player = !player;
    }

}
