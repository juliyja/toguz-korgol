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

    private static GameLogic instance;
    private ArrayList<Holes> holes = Player_Board.getButtons();
    private static Random randomIndex = new Random();

    private GameLogic() {

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
    public void move(int index) throws Exception{

        boolean player1 = index < 10;
        int count = holes.get(index).getKorgools();

        // this is here to help display a correct message on the GUI if Hole is empty
        if (count == 0){
            throw new Exception("This Hole is empty[fatal]");
        }

        if (count > 0) {
            holes.get(index).clearKorgools();

            // if the number of korgools in the current hole is 1 leave the current hole empty and update the next by one
            if (count == 1) {
                // %20 makes the array circular
                index = index % 20;
                // omit the indexes 0 and 20 as they are not part of the game
                if (index == 0 || index == 10) {
                    index++;
                }
                index++;
                holes.get(index).setKorgools(holes.get(index).getKorgools() + 1);
                // if put into own Tuz then collect to Kazan
                collectFromTuz(index);

                // minor fix so that the correction after while loop can be applied (see below)
                index++;
            }

            // if the number of korgools in the current hole is more than 1 redistribute the number of korgools collected
            else {
                while (count > 0) {
                    holes.get(index).setKorgools(holes.get(index).getKorgools() + 1);
                    collectFromTuz(index);
                    index++;
                    index = index % 20;
                    if (index == 0 || index == 10) {
                        index++;
                    }
                    count--;
                }
            }

            // since indexes 0 and 10 are not part of any player's holes, they need to be omitted
            if (index-- == 0) index = 19;
            else if (index-- == 10) index = 9;
            // while updates index beyond the last index, therefore -- corrects it
            else index--;



            // The following part is about collecting korgools from the last Hole

            // if the number of korgools is even and it's the opposite player's hole
                if (holes.get(index).getPlayer() != player1 && holes.get(index).getKorgools() % 2 == 0) {
                    holes.get(player1? 0 : 10).setKorgools(holes.get(player1? 0 : 10).getKorgools() + holes.get(index).getKorgools());
                    holes.get(index).clearKorgools();
                }

                // if there are 3 korgools in a hole and it's not the player's Hole nor the last Hole
                else if (holes.get(index).getPlayer() != player1 && holes.get(index).getKorgools() == 3 && index != (player1? 19 : 9)){
                    // if opponent doesn't have the same Hole on the opposite side chosen as a Tuz continue checking
                    if (!holes.get((index + 10)% 20).isTuz()) {
                        // if the current player didn't already make a Tuz make a Tuz of the current Hole
                        boolean onlyOneTuz = true;
                        for (int i = 1; i < 10; i++){
                            if (holes.get(i + (player1 ? 0 : 10)).isTuz()) onlyOneTuz = false;
                        }
                        if (onlyOneTuz) {
                            holes.get(index).makeTuz();
                        }
                    }
                }

                //Exceptions are here to help with the GUI to print the correct message
                if (holes.get(10).getKorgools() >= 81 || holes.get(0).getKorgools() >= 81) {

                    if (holes.get(10).getKorgools() >= 82) {
                        throw new Exception("Player1 won");

                    } else if (holes.get(0).getKorgools() >= 82) {
                        throw new Exception("Player2 won");
                    }

                    throw new Exception("It's a draw");
                }
        }
    }

    // While putting korgools they are collected immediately to the appropriate Kazan if they land in any player's Tuz
    public void collectFromTuz(int index) {
        if (holes.get(index).isTuz()){
            if (index < 10){
                holes.get(10).setKorgools(holes.get(index).getKorgools());
            }
            else {
                holes.get(0).setKorgools(holes.get(index).getKorgools());
            }
            holes.get(index).clearKorgools();
        }
    }

    // makes a random move for player1 or player2 depending which player is a CP
    public void randomMove(boolean player1) throws Exception {
        try {
            move(randomIndex.nextInt(9) + (player1? 1 : 11));
        } catch (Exception e) {
            if(e.getMessage().contains("[fatal]")){
                randomMove(player1);
            }
            else throw e;
        }
    }
}
