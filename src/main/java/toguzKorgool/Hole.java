package toguzKorgool;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class Hole extends Button {


    private boolean player;
    private int korgools;
    private boolean tuz = false;


    public Hole(boolean player, int korgools, int index){
        this.korgools = korgools;
        this.player = player;
        if(!(index == 0 || index == 10)) {
            this.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        //TODO: FOR TESTING PURPOSES ONLY
                        System.out.println("Pressed");
                        GameLogic.getInstance().move(index);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }


    public void paintButton(){
        this.setText("" + korgools);
        if(player){
            if(tuz){
                this.setStyle("-fx-background-color: White");
            }
            else this.setStyle("-fx-background-color: LightGray");
        }
        else {
            if (tuz) {
                this.setStyle("-fx-background-color: LightGray");
            } else this.setStyle("-fx-background-color: LightBlue");
        }
    }

    public int getKorgools(){
        return korgools;
    }

    public void setKorgools(int korgools){
        this.korgools = korgools;
    }

    public void clearKorgools(){
        korgools = 0;
    }

    public void makeTuz(){
        tuz = true;
    }

    public boolean isTuz(){
        return tuz;
    }

    public boolean getPlayer(){ return player; }
}
