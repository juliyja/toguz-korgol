package toguzKorgool;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Hole extends Button {


    private boolean player;
    private int korgools;
    private boolean tuz = false;


    public Hole(boolean player, int korgools, int index){
        this.korgools = korgools;
        this.player = player;
        if(!(index == 0 || index == 10) && player) {
            this.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    //TODO: FOR TESTING PURPOSES ONLY
                    System.out.println("Pressed");
                    GameLogic logic = GameLogic.getInstance();
                    switch (logic.getState()){
                        case RUNNING:
                            logic.move(index);
                        case EMPTYHOLE:
                            System.out.println(logic.getState().getDescription());
                            logic.setStateToRunning();
                        default:
                            System.out.println(GameLogic.getInstance().getState().getDescription());
                    }
                }
            });
        }
        paintButton();
    }


    public void paintButton(){
        this.setText("" + korgools);
        if(player){
            setStyle("-fx-background-color: LightGray");
        }
        else {
            setStyle("-fx-background-color: LightBlue");
        }
    }

    public int getKorgools(){
        return korgools;
    }

    public void setKorgools(int korgools){

        if(!(korgools < 0 || korgools > 162)){
            this.korgools = korgools;
        }
    }

    public void clearKorgools(){
        korgools = 0;
    }

    public void makeTuz(){
        if(!tuz){
            tuz = true;
            switchPlayer();
            paintButton();
        }
    }

    private void switchPlayer() {
        player = !player;
    }

    public boolean isTuz(){
        return tuz;
    }

    public boolean getPlayer(){ return player; }
}
