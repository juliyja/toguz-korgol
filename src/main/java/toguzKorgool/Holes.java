package main.java.toguzKorgool;


import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Holes extends Button {


    //private Player player;
    private String player;
    private int korgools;
    private boolean tuz = false;


    public Holes(String player, int korgools){
        this.korgools = korgools;
        this.player = player;
        /*if(player.equals("player")){
            this.setBackground(Color.WHITE);
        }
        else{
            this.setBackground(Color.GRAY);
        }*/
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

    public String getPlayer(){ return player; }
}
