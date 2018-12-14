package toguzKorgool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class responsible for making moves after a player has done one.
 *
 * @author Kirill Zasimov
 * @version 1.0
 */

public class AIPlayer {
    private static List<Hole> aiHoles;
    private static List<Hole> playerHoles;
    private static List<Hole> allHoles;
    private static Hole playerTuz;
    private static Hole aiTuz;
    private static final int AI_INDEX_LIMIT = 9;
    private static final int ALL_HOLES_INDEX_LIMIT = 18;
    private static final int ALL_HOLES_INDEX_MAX = 17;


    /**
     * Constructor for the AIPlayer class. Initializes all the lists with
     * holes from the PlayerBoard.
     */
    public AIPlayer(){
        playerHoles = new ArrayList<>();
        aiHoles = new ArrayList<>();
        allHoles = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            aiHoles.add(PlayerBoard.getButtons().get(i));
        }
        for(int i = 11; i < 20; i++){
            playerHoles.add(PlayerBoard.getButtons().get(i));
        }
        allHoles = new ArrayList<>();
        allHoles.addAll(aiHoles);
        allHoles.addAll(playerHoles);
    }


    /**
     * A method, which calls the move() in the GameLogic class
     * with the index that AI has decided on.
     */
    public static void move(){
        GameLogic logic = GameLogic.getInstance();
        if(canHaveTuzMove()){
            logic.move(makeTuzMove());
        }
        logic.move(makeNonTuzMove());
    }


    /**
     * A method to check if a tuz can be created with the next move.
     *
     * @return true if this move is available
     */
    private static boolean canHaveTuzMove(){
        update();
        if(aiTuz != null) {return false;}
        for(int i = 0; i < AI_INDEX_LIMIT; i++){
            int currentKorgools = aiHoles.get(i).getKorgools();
            if(currentKorgools == 0 || aiHoles.contains(allHoles.get(lastMove(i))) || lastMove(i) == ALL_HOLES_INDEX_MAX){continue;}
            if(allHoles.get(lastMove(i)).getKorgools() == 2){
                return true;
            }
        }
        return false;
    }


    /**
     * A method to make a move which results in creation of a tuz.
     *
     * @return index for GameLogic to make this move
     */
    private static int makeTuzMove(){
        for(int i = 0; i < AI_INDEX_LIMIT; i++){
            int currentKorgools = aiHoles.get(i).getKorgools();
            if(currentKorgools == 0 || aiHoles.contains(allHoles.get(lastMove(i))) || lastMove(i) == ALL_HOLES_INDEX_MAX){continue;}
            if(allHoles.get(lastMove(i)).getKorgools() == 2){
                return convertToIndex(i);
            }
        }
        return -1;
    }


    /**
     * A method to make a move which would gain the most korgools in AI's kazan.
     * It would weigh the gain against the potential loss of a korgool to a
     * player's tuz.
     * If there is no move, which would make any gains, a random move is made.
     *
     * @return index for GameLogic to make this move
     */
    private static int makeNonTuzMove(){
        update();
        int moveIndex = 0;
        int bestMove = 0;
        if(playerTuz == null){
            for(int i = 0; i < AI_INDEX_LIMIT; i++){
                int currentKorgools = aiHoles.get(i).getKorgools();
                if(currentKorgools == 0 || aiHoles.contains(allHoles.get(lastMove(i)))) {continue;}
                if((allHoles.get(lastMove(i)).getKorgools() + 1)%2 == 0){
                    int current = allHoles.get(lastMove(i)).getKorgools() + 1;
                    if(current > bestMove){
                        bestMove = current;
                        moveIndex = i;
                    }
                }
            }
        }else{
            for(int i = 0; i < AI_INDEX_LIMIT; i++){
                int currentKorgools = aiHoles.get(i).getKorgools();
                if(currentKorgools == 0 || aiHoles.contains(allHoles.get(lastMove(i)))) {continue;}
                if((allHoles.get(lastMove(i)).getKorgools() + 1)%2 == 0){
                    int current = allHoles.get(lastMove(i)).getKorgools() + 1;
                    for(int j = 1; j < currentKorgools - 1; j++){
                        if(allHoles.get((i + j)%ALL_HOLES_INDEX_LIMIT) == playerTuz){
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
        if(bestMove == 0){
            Random rand = new Random();
            moveIndex = rand.nextInt(8);
        }
        return convertToIndex(moveIndex);
    }


    /**
     * A method to update if a tuz was made.
     */
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


    /**
     * A method to find an index of a hole where the last korgool is placed.
     *
     * @param index index of the hole a move is made from
     * @return index of a hole where the last korgool is placed.
     */
    private static int lastMove(int index){
        int moveIndex = (index + (aiHoles.get(index).getKorgools() - 1));
        return moveIndex%ALL_HOLES_INDEX_LIMIT;
    }


    /**
     * A method to convert the index of a hole into one accepted by GameLogic.
     *
     * @param index index used in AIPlayer
     * @return index accepted by GameLogic
     */
    private static int convertToIndex(int index){
        if(index < AI_INDEX_LIMIT){
            return index + 1;
        } else {
            return index + 2;
        }
    }
}
