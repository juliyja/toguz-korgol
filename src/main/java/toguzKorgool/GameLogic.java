package toguzKorgool;

import java.util.ArrayList;
import java.util.Random;

/**
 * The application's logic behind the game.
 *
 * @Julia Julia-Jakubiak
 * @25/11/2018
 */

public class GameLogic {

    private static final int WINNING_SCORE = 81;
    private static final int P1_KAZAN = 0;
    private static final int P2_KAZAN = 10;
    private static final int P1_LAST_INDEX = 9;
    private static final int P2_LAST_INDEX = 19;
    private static final int BOARD_SIZE = 20;
    private static GameLogic instance;
    private ArrayList<Hole> holes = PlayerBoard.getButtons();
    private static Random randomIndex = new Random();
    private static GameState state;

    private GameLogic() {
        state = GameState.RUNNING;
    }

    // Ensure use of only one game logic throughout the whole project
    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    // To remember: from 1-9 indexes are the holes of player1, indexes 11-19 are holes of player2

    /* Moves korgools selected from a Hole @param: index,  in the
     * counterclockwise direction, following the stated rules of the game
     */
    public void move(int index){

        boolean player1 = index < P2_KAZAN;
        int count = holes.get(index).getKorgools();

        // this is here to help display a correct message on the GUI if Hole is empty
        if (count == P1_KAZAN){
            state = GameState.EMPTYHOLE;
        }

        if (count > P1_KAZAN) {
            holes.get(index).clearKorgools();

            // if the number of korgools in the current hole is 1 leave the current hole empty and update the next by one
            if (count == 1) {
                index = moveOneKorgool(index);
            }

            // if the number of korgools in the current hole is more than 1 redistribute the number of korgools collected
            else {
                index = moveMultipleKorgools(index, count);
            }

            // since indexes 0 and 10 are not part of any player's holes, they need to be omitted
            index = adjustIndex(index);


            // The following part is about collecting korgools from the last Hole

            // if the number of korgools is even and it's the opposite player's hole
                if (holes.get(index).getPlayer() != player1 && holes.get(index).getKorgools() % 2 == P1_KAZAN) {
                    collectPoints(index, player1);
            }

                // if there are 3 korgools in a hole and it's not the player's Hole nor the last Hole
                else if (holes.get(index).getPlayer() != player1 && holes.get(index).getKorgools() == 3 && index != (player1? P2_LAST_INDEX : P1_LAST_INDEX)){
                    makeTuz(index, player1);

                }

            setState();
        }
            PlayerBoard.updateBoard();
    }

    private void setState() {
        if (holes.get(P2_KAZAN).getKorgools() >= WINNING_SCORE || holes.get(P1_KAZAN).getKorgools() >= WINNING_SCORE) {

            if (holes.get(P2_KAZAN).getKorgools() > WINNING_SCORE) {
                state = GameState.P1WON;

            } else if (holes.get(P1_KAZAN).getKorgools() > WINNING_SCORE) {
                state = GameState.P2WON;
            }

            state = GameState.DRAW;
        }
    }

    private void makeTuz(int index, boolean player1) {
        // if opponent doesn't have the same Hole on the opposite side chosen as a Tuz continue checking
        if (!holes.get((index + P2_KAZAN)% BOARD_SIZE).isTuz()) {
            // if the current player didn't already make a Tuz make a Tuz of the current Hole
            boolean onlyOneTuz = true;
            for (int i = 1; i < P2_KAZAN; i++){
                if (holes.get(i + (player1 ? P1_KAZAN : P2_KAZAN)).isTuz()) onlyOneTuz = false;
            }
            if (onlyOneTuz) {
                holes.get(index).makeTuz();
                collectFromTuz(index);
            }
        }
    }

    private void collectPoints(int index, boolean player1) {
        holes.get(player1? P1_KAZAN : P2_KAZAN).setKorgools(holes.get(player1? P1_KAZAN : P2_KAZAN).getKorgools() + holes.get(index).getKorgools());
        holes.get(index).clearKorgools();
    }

    private int adjustIndex(int index) {
        if (index == 1) index = P2_LAST_INDEX;
        else if (index == 11) index = P1_LAST_INDEX;
        // while updates index beyond the last index, therefore -- corrects it
        else index--;
        return index;
    }

    private int moveMultipleKorgools(int index, int count) {
        while (count > P1_KAZAN) {
            holes.get(index).setKorgools(holes.get(index).getKorgools() + 1);
            collectFromTuz(index);
            index++;
            index = index % BOARD_SIZE;
            if (index == P1_KAZAN || index == P2_KAZAN) {
                index++;
            }
            count--;
        }
        return index;
    }

    private int moveOneKorgool(int index) {
        index = index % BOARD_SIZE;
        // omit the indexes 0 and 20 as they are not part of the game
        if (index == P2_LAST_INDEX || index == P1_LAST_INDEX) {
            index++;
        }
        index++;
        index = index % BOARD_SIZE;
        holes.get(index).setKorgools(holes.get(index).getKorgools() + 1);
        // if put into own Tuz then collect to Kazan
        collectFromTuz(index);

        // minor fix so that the correction after while loop can be applied (see below)
        index++;
        return index;
    }

    // While putting korgools they are collected immediately to the appropriate Kazan if they land in any player's Tuz
    public void collectFromTuz(int index) {
        if (holes.get(index).isTuz()){
            if (index < P2_KAZAN){
                holes.get(P2_KAZAN).setKorgools(holes.get(P2_KAZAN).getKorgools() + holes.get(index).getKorgools());
            }
            else {
                holes.get(P1_KAZAN).setKorgools(holes.get(P1_KAZAN).getKorgools() + holes.get(index).getKorgools());
            }
            holes.get(index).clearKorgools();
        }
    }

    // makes a random move for player1 or player2 depending which player is a CP
    public void randomMove(boolean player1){
        if(state.getIfFatal()){
            randomMove(player1);
        } else {
            move(randomIndex.nextInt(9) + (player1? 1 : 11));
        }
    }

    public GameState getState(){
        return state;
    }

    public static void setStateToRunning(){
        state = GameState.RUNNING;
    }

}
