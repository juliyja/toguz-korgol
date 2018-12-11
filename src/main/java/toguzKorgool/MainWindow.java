package toguzKorgool;

import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;

/**
 * The application's main window for the user.
 *
 * @author Ido Ben-zvi
 * @version 15/11/2018
 */
public class MainWindow {

    private static JFrame frame;

    public static void main(String[] args)throws InterruptedException{
        new MainWindow();
    }

    /**
     * Set up main window.
     */
    private MainWindow() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            latch.countDown();
        });
        latch.await();

        frame = new JFrame("Main Menu");
        setUpMainWindow();
        setUpMenu();
    }

    /**
     * Creating main window.
     */
    private static void setUpMainWindow()
    {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(4, 1));


        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300, 450);
    }

    private static void setUpMenu(){
        JButton singlePlayer = new JButton("Single Player");
        singlePlayer.setPreferredSize(new Dimension(150, 100));

        singlePlayer.addActionListener(e -> {
            new FileEditor(true, "");
            Player_Board.launch();
        });

        JButton loadGame = new JButton ("Load Game");
        loadGame.setPreferredSize(new Dimension(150, 100));

        loadGame.addActionListener(e -> {
            new FileEditor(false, "save");
            Player_Board.launch();
        });

        JButton instructions  = new JButton ("Intructions");
        instructions.addActionListener(e -> setUpInstructions());

        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        singlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(singlePlayer);
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(loadGame);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(instructions);
    }

    private static void setUpInstructions(){
        JOptionPane.showMessageDialog(frame,
                "Move\n" +
                        "Players make moves by selecting one of the holes on their side of the game that contains korgools. \n" +
                        "A move consists of taking all the korgools from the selected hole\n"
                        + " and redistributing or seeding them to other holes in the anticlockwise direction. The first korgool is put in the hole the korgools were taken from. The next korgool\n"
                        + "goes in the adjacent hole to the right, and so on. Once the player has dropped a korgool in hole 9, the next korgool goes into hole 1 of the other player, and so on.\n\n"

                        +"Rules\n" +
                        "1. The player on the white/light side makes the first move. Players alternate between making moves.\n"
                        + "2.The normal redistribution rule does not apply if there is only one korgool in a hole. If there is only one korgool \n" +
                        "in a hole and that hole is selected for a move, the hole is emptied and the korgool is moved to the next hole.\n"
                        + "3. If the last korgool in a move ends up in an opponent's hole, and the number of korgools in that hole is now even, then the player \n" +
                        "captures all the korgools in that hole. The korgools are moved into the player's kazan.\n"
                        + "4. If the last korgool in a move ends up in the player's own side, nobody captures these korgools.\n"
                        + "5. If the last korgool in a move ends up in an opponent's hole, and the number of korgools in that hole is now odd, then nobody captures these korgools.\n"
                        + "6. If the last korgool in a move ends up in an opponent's hole containing two korgools (i.e. the hole contains three korgools at the end of the move), \n" +
                        "then this hole is marked as a tuz.\n"
                        +"   This means that this hole now belongs to the player who claimed the tuz and all korgools in the tuz are transferred to the owner's kazan. \n" +
                        "The following restrictions apply:\n"
                        + "  - Each player can claim only one tuz.\n"
                        + "  - A tuz cannot be moved.\n"
                        + "  - Hole 9 cannot be claimed as tuz.\n"
                        + "  - If one player has claimed hole n as tuz, then their opponent can no longer claim hole n on the opposite side as tuz.\n"
                        + "7. The game ends when one player has collected 82 or more korgools in their kazan.");
    }
}