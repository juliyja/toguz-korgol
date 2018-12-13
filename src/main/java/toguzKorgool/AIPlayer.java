package toguzKorgool;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer {
    private static List<Hole> aiHoles;
    private static List<Hole> playerHoles;
    private static List<Hole> allHoles;
    private static Hole playerTuz;
    private static Hole aiTuz;
    private static final int ADDITIVE = 9;


    public AIPlayer(){
        playerHoles = new ArrayList<>();
        aiHoles = new ArrayList<>();
        allHoles = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            aiHoles.add((Hole)Player_Board.getButtons().get(i));
        }
        for(int i = 11; i < 20; i++){
            playerHoles.add((Hole)Player_Board.getButtons().get(i));
        }
        allHoles = new ArrayList<>();
        allHoles.addAll(aiHoles);
        allHoles.addAll(playerHoles);
    }

    public static void move(){
        GameLogic logic = GameLogic.getInstance();
        if(canHaveTuzMove()){
            logic.move(makeTuzMove());
        }
        logic.move(makeNonTuzMove());
    }

    private static boolean canHaveTuzMove(){
        update();
        if(aiTuz != null) {return false;}
        for(int i = 0; i < 9; i++){
            int currentKorgools = aiHoles.get(i).getKorgools();
            if(currentKorgools == 0 || lastMove(i) ==8 || lastMove(i) == 17){continue;}
            if(allHoles.get(lastMove(i)).getKorgools() == 2){
                return true;
            }
        }
        return false;
    }

    private static int makeTuzMove(){
        for(int i = 0; i < 9; i++){
            int currentKorgools = aiHoles.get(i).getKorgools();
            if(currentKorgools == 0 || lastMove(i) == 8 || lastMove(i) == 17){continue;}
            if(allHoles.get(lastMove(i)).getKorgools() == 2){
                return convertToIndex(i);
            }
        }
        return -1;
    }

    private static int makeNonTuzMove(){
        update();
        int moveIndex = 0;
        int bestMove = 0;
        if(playerTuz == null){
            for(int i = 0; i < 9; i++){
                int currentKorgools = aiHoles.get(i).getKorgools();
                if(currentKorgools == 0 || lastMove(i) < 9) {continue;}
                if((allHoles.get(lastMove(i)).getKorgools() + 1)%2 == 0){
                    int current = allHoles.get(lastMove(i)).getKorgools() + 1;
                    if(current > bestMove){
                        bestMove = current;
                        moveIndex = i;
                    }
                }
            }
        }else{
            for(int i = 0; i < 9; i++){
                int currentKorgools = aiHoles.get(i).getKorgools();
                if(currentKorgools == 0 || lastMove(i) < 9) {continue;}
                if((allHoles.get(lastMove(i)).getKorgools() + 1)%2 == 0){
                    int current = allHoles.get(lastMove(i)).getKorgools() + 1;
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
        return convertToIndex(moveIndex);
    }

    private static void update(){
        for(Hole hole: allHoles){
            if(hole.getPlayer() && hole.isTuz()){
                playerTuz = hole;
            }
            if(!hole.getPlayer() && hole.isTuz()){
                aiTuz = hole;
            }
        }
    }

    private static int lastMove(int i){
        int moveIndex = (i + (aiHoles.get(i).getKorgools() - 1));
        if(moveIndex > 17) {return moveIndex%17 - 1;}
        else {return moveIndex;}
    }

    private static int convertToIndex(int index){
        if(index < 9){
            return index + 1;
        } else {
            return index + 2;
        }
    }
}
