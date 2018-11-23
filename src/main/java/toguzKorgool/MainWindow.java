package main.java.toguzKorgool;

import java.awt.*;
import javax.swing.*;

/**
 * The application's main window for the user.
 *
 * @Ido Ben-zvi
 * @15/11/2018
 */
public class MainWindow {

    private static JFrame frame;

    public static void main(String[] args){
        MainWindow window = new MainWindow();
    }

    /**
     * Set up main window.
     */
    public MainWindow()
    {
        frame = new JFrame("Main Menu");
        setUpMainWindow();
        setUpMenu();
    }

    /**
     * Creating main window.
     */
    private static void setUpMainWindow()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));

        setUpMenu();

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private static void setUpMenu(){
        JButton singlePlayer = new JButton("Single Player");
        //singlePlayer.setPreferredSize(new Dimension(200, 100));
        JButton loadGame = new JButton ("Load Game");
        //loadGame.setPreferredSize(new Dimension(200, 100));

        //Container pane = frame.getContentPane();
        frame.add(singlePlayer);
        frame.add(loadGame);
    }
}
