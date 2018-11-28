package toguzKorgool;


import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

//one singular button (yes, HoleS)
public class Holes extends Button {


    private boolean player;
    private int korgools;
    private boolean tuz = false;


    public Holes(boolean player, int korgools){
        this.korgools = korgools;
        this.player = player;
        if(player){
            if(tuz){
                this.setStyle("-fx-background-color: White");
            }
            else this.setStyle("-fx-background-color: LightGray");
        }
        else{
            if(tuz){
                this.setStyle("-fx-background-color: LightGray");
            }
            else this.setStyle("-fx-background-color: White");
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
