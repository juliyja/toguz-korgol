package toguzKorgool;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.ImagePattern;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class PlayerBoard {

    // The pane containing the Holes on the board.
    private static GridPane board;
    //The array of Holes displayed on the board.
    private static ArrayList<Hole> buttons = new ArrayList<>();
    //The instance of the PlayerBoard
    private static PlayerBoard instance;
    //The frame of the Board.
    private static JFrame frame;
    //The panel of the Board.
    private static JFXPanel fxPanel;


    public PlayerBoard(){
        if(instance == null){
            launch();
            instance = this;
        }
        else {
            refreshState();
            instance = this;
        }
    }

    public static void launch(){
        initializeButtons();
        SwingUtilities.invokeLater(() -> initAndShowGUI());
    }

    public static void refreshState() {
        buttons.clear();
        launch();
    }

    public static PlayerBoard getInstance(){
        if(instance != null){
            new PlayerBoard();
        }
        return instance;
    }
    private static void initAndShowGUI() {
        board = new GridPane();
        frame = new JFrame("Toguz Korgool");
        fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(1200, 650);
        frame.repaint();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        Platform.runLater(() -> initFX(fxPanel));
        new AIPlayer();
        frame.setVisible(true);
    }


    /**
     * A getter method that returns the Holes contained by the Player Board.
     *
     * @return the Holes contained by the Player Board.
     */
    public static ArrayList<Hole> getButtons() {
        return buttons;
    }

    private static void setColumnWidth() {
        for (int i = 0; i < 9; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(111));
        }
    }

    public static JFrame getFrame(){return frame;}

    public static JFXPanel getPanel(){return fxPanel;}

    /**
     * A setter method used to set the height of each row of the GridPane.
     */
    private static void setRowHeight() {
        for (int i = 0; i < 5; i++) {
            if (i == 0 || i == 4) {
                board.getRowConstraints().add(new RowConstraints(50));
            } else board.getRowConstraints().add(new RowConstraints(150));
        }
    }

    private static void initFX(JFXPanel fxPanel) {

        setColumnWidth();
        setRowHeight();

        board.setAlignment(Pos.CENTER);

        board.setPrefSize(1400, 600);

        board.setGridLinesVisible(true);

        board.setVgap(10);
        board.setHgap(10);

        board.setStyle("-fx-background-color: transparent;");

        updateBoard();


        BorderPane pane = new BorderPane();
        pane.setMaxSize(1400, 600);
        pane.setCenter(board);

        // This method is invoked on the JavaFX thread

        Scene scene = new Scene(pane, 1200, 600);
        scene.setFill(new ImagePattern(new Image("file:./src/main/java/toguzKorgool/board.jpg")));
        fxPanel.setScene(scene);

    }

    public static void updateBoard(){
        //Clear the board's components before adding new ones.
        board.getChildren().clear();



        updateButtons();

        //Populate each row and column of the GridPane with labels or buttons.
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 9; c++) {
                if (r == 0) {
                    Label label = new Label(9 - c + "");
                    label.setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    label.setAlignment(Pos.CENTER);
                    board.add(label, c, r, 1, 1);
                } else if (r == 4) {
                    Label label = new Label(c + 1 + "");
                    label.setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    label.setAlignment(Pos.CENTER);
                    board.add(label, c, r, 1, 1);
                } else if (r == 1) {
                    buttons.get(9-c).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(9-c), c, r, 1, 1);
                } else if (r == 3) {
                    buttons.get(c + 11).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(c + 11), c, r, 1, 1);
                } else {
                    buttons.get(0).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    buttons.get(10).setPrefSize(board.getPrefWidth(), board.getPrefHeight());
                    board.add(buttons.get(0), c, r, 4, 1);
                    board.add(buttons.get(10), 5, r, 4, 1);
                    break;
                }
            }
        }
    }

    /**
     * A method that refreshes the state of each Hole on the board.
     */
    private static void updateButtons(){
        for (Hole button : buttons) {
            button.updateHole();
        }
    }


    /**
     * Based on the information provided by the FileEditor class the buttons array is populated
     * with instances of the Hole class.
     */
    private static void initializeButtons(){
        for (int i = 0; i < FileEditor.getDataList().size(); i++) {
            Hole newButton = new Hole(FileEditor.getPlayer().get(i), FileEditor.getDataList().get(i), i);
            newButton.setId("Button" + i);
            buttons.add(newButton);
        }
    }

    public static void gameEndAlert(String description){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game finished!");
        alert.setHeaderText(description);
        alert.setContentText("You can either go to main menu or exit the game.");

        ButtonType buttonTypeExit = new ButtonType("Exit");
        ButtonType buttonTypeMenu = new ButtonType("Main Menu");

        alert.getButtonTypes().setAll(buttonTypeExit, buttonTypeMenu);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeExit){
            Platform.exit();
        }else {
            Platform.exit();
            try{
                new MainWindow();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}

