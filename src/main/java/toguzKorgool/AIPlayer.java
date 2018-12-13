package toguzKorgool;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer {
    private List<Hole> aiHoles;
    private List<Hole> playerHoles;
    private List<Hole> allHoles;
    private Hole playerTuz;
    private Hole aiTuz;
    private static final int ADDITIVE = 9;


    public AIPlayer(){
        playerHoles = new ArrayList<>();
        aiHoles = new ArrayList<>();
        allHoles = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            playerHoles.add((Hole)PlayerBoard.getButtons().get(i));
        }
        for(int i = 11; i < 20; i++){
            aiHoles.add((Hole)PlayerBoard.getButtons().get(i));
        }
        for(int i = 1; i < 20 && i != 10; i++){
            allHoles.add((Hole)PlayerBoard.getButtons().get(i));
        }
    }

    public void move(){
        GameLogic logic = GameLogic.getInstance();
        if(canHaveTuzMove()){
            logic.move(makeTuzMove() + ADDITIVE);
        }
        logic.move(makeNonTuzMove() + ADDITIVE);
    }

    private boolean canHaveTuzMove(){
        update();
        if(aiTuz != null) {return false;}
        for(int i = 0; i < 9; i++){
            int currentKorgools = aiHoles.get(i).getKorgools();
            if(currentKorgools == 0){continue;}
            if(allHoles.get(9 + i + (currentKorgools - 1)).getKorgools() + 1 == 2){
                return true;
            }
        }
        return false;
    }

    private int makeTuzMove(){
        for(int i = 0; i < 9; i++){
            int currentKorgools = aiHoles.get(i).getKorgools();
            if(currentKorgools == 0){continue;}
            if(allHoles.get((9 + i + (currentKorgools - 1))%17).getKorgools() + 1 == 2){
                return i;
            }
        }
        return -1;
    }

    private int makeNonTuzMove(){
        update();
        int moveIndex = 0;
        int bestMove = 0;
        if(playerTuz == null){
            for(int i = 0; i < 9; i++){
                int currentKorgools = aiHoles.get(i).getKorgools();
                if(currentKorgools == 0) {continue;}
                if((allHoles.get((9 + i + (currentKorgools - 1))%17).getKorgools() + 1)%2 == 0){
                    int current = allHoles.get((9 + i + (currentKorgools - 1))%17).getKorgools() + 1;
                    if(current > bestMove){
                        bestMove = current;
                        moveIndex = i;
                    }
                }
            }
        }else{
            for(int i = 0; i < 9; i++){
                int currentKorgools = aiHoles.get(i).getKorgools();
                if(currentKorgools == 0) {continue;}
                if((allHoles.get((9 + i +(currentKorgools - 1))%17).getKorgools() + 1)%2 == 0){
                    int current = allHoles.get((9 + i + (currentKorgools - 1))%17).getKorgools() + 1;
                    for(int j = 0; j < currentKorgools - 1; j++){
                        if(allHoles.get((9 + i + (j - 1))%17) == playerTuz){
                            current--;
                        }
                    }
                    if(current > bestMove){
                        bestMove = current;
                        moveIndex = i;
                    }
                }
            }
        }
        return moveIndex;
    }

    private void update(){
        for(Hole hole: allHoles){
            if(hole.getPlayer() && hole.isTuz()){
                playerTuz = hole;
            }
            if(!hole.getPlayer() && hole.isTuz()){
                aiTuz = hole;
            }
        }
    }
}
