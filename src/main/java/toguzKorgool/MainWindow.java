package main.java.toguzKorgool;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;

import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;

/**
 * The application's main window for the user.
 *
 * @Ido Ben-zvi
 * @15/11/2018
 */
public class MainWindow {

    private static JFrame frame;

    public static void main(String[] args)throws InterruptedException{
        MainWindow window = new MainWindow();
    }

    /**
     * Set up main window.
     */
    public MainWindow() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JFXPanel();
                latch.countDown();
            }
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new GridLayout(4, 1));


        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1200, 1200);
    }

    private static void setUpMenu(){
        JButton singlePlayer = new JButton("Single Player");
        singlePlayer.setPreferredSize(new Dimension(200, 100));

        singlePlayer.addActionListener(new ActionListener() {

                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               try {
                                                   Player_Board playerBoard = new Player_Board();
                                                   playerBoard.launch();

                                               } catch (IOException e1) {
                                                   System.out.println("ERROR");
                                               }
                                           }
                                       });

        JButton loadGame = new JButton ("Load Game");
        loadGame.setPreferredSize(new Dimension(1000, 800));

        JButton instructions  = new JButton ("Intructions");

        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(loadGame);
        System.out.println("goallllllllll");
        singlePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(singlePlayer);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(instructions);
    }
}
