package toguzKorgool;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;


/**
 * The class in charge of visualizing the board, populating it with Holes and interacting with the user.
 * After every move made either by active or AI player the board and all of it's underlying components is updated
 * to show a new state.
 * The class extends Application to provide a singleton functionality preventing the creation of multiple PlayerBoards
 * simultaneously leading to multiple threads manipulating the same text files.
 *
 * @author Emiliyana Tsanova
 * @version 1.0
 */

public class PlayerBoard extends Application {

    //The array of Holes displayed on the board.
    private static ArrayList<Hole> buttons = new ArrayList<>();
    // The pane containing the GridPane
    private static BorderPane pane = new BorderPane();
    // The pane containing the Holes on the board.
    private static GridPane board = new GridPane();

    /**
     * A constructor for the PlayerBoard. It does not take parameters and
     * call the initializeButtons method.
     */
    public PlayerBoard(){
        initializeButtons();
        new AIPlayer();
    }

    /**
     * The method used by default by the Application class to launch. It calls the constructor of the PlayerBoard class
     * and the overwritten start method.
     */
    public static void launch() {
        Application.launch();
    }


    /**
     * The method setting up the Application's view. It calls methods setting up different components in
     * the correct order.
     *
     * @param primaryStage the Stage where the Application will be executed.
     */
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Toguz Korgool");

        setUpBoard();

        setUpBorderPane();

        setPrimaryStage(primaryStage);
    }

    /**
     * Updates the state of the board by updating the components contained.
     */
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
     * A getter method that returns the Holes contained by the Player Board.
     *
     * @return the Holes contained by the Player Board.
     */
    public static ArrayList<Hole> getButtons() {
        return buttons;
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

    public static void reinitializeBoard(){
        buttons = new ArrayList<>();
        initializeButtons();
        updateButtons();
    }



    /**
     * A method to set up the view of the primary stage of the player board.
     *
     * @param primaryStage the stage the Application will be executed in.
     */
    private void setPrimaryStage(Stage primaryStage){
        Scene scene = setUpScene();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * A method to set up the scene for the application's Stage.
     *
     * @return the Scene for the application's stage.
     */
    private static Scene setUpScene(){
        Scene scene = new Scene(pane, 1200, 600);
        scene.setFill(new ImagePattern(new Image("file:./src/main/java/toguzKorgool/board.jpg")));
        return scene;
    }

    /**
     * A method to set up the Border Pane containing the GridPane.
     */
    private static void setUpBorderPane(){
        pane.setMaxSize(1400, 600);
        pane.setCenter(board);
    }

    /**
     * A method to set up the view of the GridPane representing the player board the user interacts with.
     */
    private static void setUpBoard(){
        setColumnWidth();
        setRowHeight();

        board.setAlignment(Pos.CENTER);

        board.setPrefSize(1400, 600);

        board.setGridLinesVisible(true);

        board.setVgap(10);
        board.setHgap(10);

        board.setStyle("-fx-background-color: transparent;");

        updateBoard();
    }

    /**
     * A setter used to set the width of the columns of the GridPane.
     */
    private static void setColumnWidth() {
        for (int i = 0; i < 9; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(111));
        }
    }

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


    /**
     * A method that refreshes the state of each Hole on the board.
     */
    private static void updateButtons(){
        for (Hole button : buttons) {
            button.updateHole();
        }
    }
}
